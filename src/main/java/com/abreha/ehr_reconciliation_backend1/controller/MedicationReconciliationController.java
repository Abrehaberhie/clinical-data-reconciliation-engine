package com.abreha.ehr_reconciliation_backend1.controller;

import com.abreha.ehr_reconciliation_backend1.dto.MedicationReconciliationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.MedicationReconciliationResponse;
import com.abreha.ehr_reconciliation_backend1.service.MedicationReconciliationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reconcile")
public class MedicationReconciliationController {

    private final MedicationReconciliationService service;

    public MedicationReconciliationController(MedicationReconciliationService service) {
        this.service = service;
    }

    @PostMapping("/medication")
    public ResponseEntity<MedicationReconciliationResponse> reconcileMedication(
            @RequestBody MedicationReconciliationRequest request) {

        MedicationReconciliationResponse response =
                service.reconcileMedication(request);

        return ResponseEntity.ok(response);
    }
}
