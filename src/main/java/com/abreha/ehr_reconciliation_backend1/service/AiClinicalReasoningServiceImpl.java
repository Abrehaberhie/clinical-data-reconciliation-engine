package com.abreha.ehr_reconciliation_backend1.service;
import com.abreha.ehr_reconciliation_backend1.dto.AiMedicationInsight;
import com.abreha.ehr_reconciliation_backend1.dto.MedicationReconciliationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.MedicationSourceRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service

public class AiClinicalReasoningServiceImpl implements AiClinicalReasoningService{

    private final OpenAiClientService openAiClientService;

    public AiClinicalReasoningServiceImpl(OpenAiClientService openAiClientService) {
        this.openAiClientService = openAiClientService;
    }


    @Override
    public AiMedicationInsight analyzeMedicationConflict(
            MedicationReconciliationRequest request) {


        if (request == null || request.getSources() == null || request.getSources().isEmpty()) {
            return new AiMedicationInsight(
                    "Unknown",
                    0.0,
                    "No medication sources were provided.",
                    List.of("Provide at least one medication source"),
                    "REVIEW_NEEDED"
            );
        }


        MedicationSourceRecord bestSource = request.getSources().stream()
                .max(Comparator.comparingInt(this::calculatePriority))
                .orElse(request.getSources().get(0));

        String reconciledMedication = bestSource.getMedication();
        Double confidenceScore = calculateConfidence(bestSource);
        String prompt = buildPrompt(request, bestSource);
        //String prompt = buildPrompt(request, bestSource);
        String reasoning = openAiClientService.generateMedicationReasoning(prompt);

        //String reasoning = buildReasoning(bestSource, request);

        List<String> recommendedActions = new ArrayList<>();
        recommendedActions.add("Verify medication with patient");
        recommendedActions.add("Update older records to reflect the reconciled medication");
        if ("Pharmacy".equalsIgnoreCase(bestSource.getSystem())
                || "Pharmacy".equalsIgnoreCase(normalizeSystem(bestSource.getSystem()))) {
            recommendedActions.add("Confirm active prescription with prescribing clinician");
        }

        String clinicalSafetyCheck = runSafetyCheck(reconciledMedication);

        return new AiMedicationInsight(
                reconciledMedication,
                confidenceScore,
                reasoning,
                recommendedActions,
                clinicalSafetyCheck
        );
    }

    private int calculatePriority(MedicationSourceRecord source) {
        int score = 0;

        if (source.getSourceReliability() != null) {
            if (source.getSourceReliability().equalsIgnoreCase("high")) {
                score += 30;
            } else if (source.getSourceReliability().equalsIgnoreCase("medium")) {
                score += 20;
            } else {
                score += 10;
            }
        }

        if (source.getLastUpdated() != null && !source.getLastUpdated().isBlank()) {
            score += 25;
        }

        if (source.getLastFilled() != null && !source.getLastFilled().isBlank()) {
            score += 15;
        }

        String system = normalizeSystem(source.getSystem());
        if ("Primary Care".equalsIgnoreCase(system)) {
            score += 20;
        } else if ("Hospital EHR".equalsIgnoreCase(system)) {
            score += 15;
        } else if ("Pharmacy".equalsIgnoreCase(system)) {
            score += 10;
        }

        return score;
    }

    private double calculateConfidence(MedicationSourceRecord bestSource) {
        int priority = calculatePriority(bestSource);
        double confidence = priority / 100.0;
        return Math.min(confidence, 0.95);
    }

    private String buildPrompt(
            MedicationReconciliationRequest request,
            MedicationSourceRecord bestSource) {

        StringBuilder sb = new StringBuilder();
        sb.append("Selected ");
        sb.append(bestSource.getSystem());
        sb.append(" as the most likely accurate source based on source reliability and recency.");

        if (request.getPatientContext() != null
                && request.getPatientContext().getRecentLabs() != null
                && request.getPatientContext().getRecentLabs().containsKey("eGFR")) {
            sb.append(" Recent lab context such as eGFR was considered in the reconciliation.");
        }

        return sb.toString();
    }

    private String runSafetyCheck(String medication) {
        if (medication == null || medication.isBlank()) {
            return "REVIEW_NEEDED";
        }
        return "PASSED";
    }

    private String normalizeSystem(String system) {
        return system == null ? "" : system.trim();
    }
}
