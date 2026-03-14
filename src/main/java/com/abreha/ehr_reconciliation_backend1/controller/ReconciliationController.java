package com.abreha.ehr_reconciliation_backend1.controller;

import com.abreha.ehr_reconciliation_backend1.dto.ReconciliationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.ReconciliationResponse;
import com.abreha.ehr_reconciliation_backend1.service.ReconciliationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reconciliation")
public class ReconciliationController {

    private final ReconciliationService reconciliationService;

    public ReconciliationController(ReconciliationService reconciliationService) {
        this.reconciliationService = reconciliationService;
    }

    @PostMapping
    public ResponseEntity<ReconciliationResponse> reconcilePatientData(
            @RequestBody ReconciliationRequest request) {

        ReconciliationResponse response =
                reconciliationService.reconcileRecords(request);

        return ResponseEntity.ok(response);
    }
}
