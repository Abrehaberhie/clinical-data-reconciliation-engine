package com.abreha.ehr_reconciliation_backend1.service;
import com.abreha.ehr_reconciliation_backend1.dto.AiMedicationInsight;
import com.abreha.ehr_reconciliation_backend1.dto.MedicationReconciliationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.MedicationReconciliationResponse;
import org.springframework.stereotype.Service;

@Service
public class MedicationReconciliationServiceImpl implements MedicationReconciliationService{

    private final AiClinicalReasoningService aiClinicalReasoningService;

    public MedicationReconciliationServiceImpl(AiClinicalReasoningService aiClinicalReasoningService) {
        this.aiClinicalReasoningService = aiClinicalReasoningService;
    }

    @Override
    public MedicationReconciliationResponse reconcileMedication(
            MedicationReconciliationRequest request) {

        AiMedicationInsight aiInsight =
                aiClinicalReasoningService.analyzeMedicationConflict(request);

        return new MedicationReconciliationResponse(
                aiInsight.getReconciledMedication(),
                aiInsight.getConfidenceScore(),
                aiInsight.getReasoning(),
                aiInsight.getRecommendedActions(),
                aiInsight.getClinicalSafetyCheck()
        );
    }
}
