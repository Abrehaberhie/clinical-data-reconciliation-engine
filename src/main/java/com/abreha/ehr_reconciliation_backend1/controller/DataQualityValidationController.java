package com.abreha.ehr_reconciliation_backend1.controller;

import com.abreha.ehr_reconciliation_backend1.dto.DataQualityValidationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.DataQualityValidationResponse;
import com.abreha.ehr_reconciliation_backend1.service.DataQualityValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validate")
public class DataQualityValidationController {
    private final DataQualityValidationService service;

    public DataQualityValidationController(DataQualityValidationService service) {
        this.service = service;
    }

    @PostMapping("/data-quality")
    public ResponseEntity<DataQualityValidationResponse> validateDataQuality(
            @RequestBody DataQualityValidationRequest request) {

        DataQualityValidationResponse response = service.validateDataQuality(request);
        return ResponseEntity.ok(response);
    }
}
