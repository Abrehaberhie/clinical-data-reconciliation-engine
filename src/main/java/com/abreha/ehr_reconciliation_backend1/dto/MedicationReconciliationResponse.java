package com.abreha.ehr_reconciliation_backend1.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationReconciliationResponse {
    @JsonProperty("reconciled_medication")
    private String reconciledMedication;

    @JsonProperty("confidence_score")
    private Double confidenceScore;

    private String reasoning;

    @JsonProperty("recommended_actions")
    private List<String> recommendedActions;

    @JsonProperty("clinical_safety_check")
    private String clinicalSafetyCheck;
}
