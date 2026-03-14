package com.abreha.ehr_reconciliation_backend1.service;
import com.abreha.ehr_reconciliation_backend1.dto.MedicationReconciliationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.MedicationReconciliationResponse;

public interface MedicationReconciliationService {
    MedicationReconciliationResponse reconcileMedication(
            MedicationReconciliationRequest request);
}
