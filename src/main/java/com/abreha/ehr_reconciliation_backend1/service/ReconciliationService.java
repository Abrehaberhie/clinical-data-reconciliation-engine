package com.abreha.ehr_reconciliation_backend1.service;
import com.abreha.ehr_reconciliation_backend1.dto.ReconciliationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.ReconciliationResponse;

public interface ReconciliationService {
    ReconciliationResponse  reconcileRecords(ReconciliationRequest request);

}
