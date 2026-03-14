package com.abreha.ehr_reconciliation_backend1.service;
import com.abreha.ehr_reconciliation_backend1.dto.AiMedicationInsight;
import com.abreha.ehr_reconciliation_backend1.dto.MedicationReconciliationRequest;
public interface AiClinicalReasoningService {
    AiMedicationInsight analyzeMedicationConflict(
            MedicationReconciliationRequest request
    );
}
