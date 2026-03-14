package com.abreha.ehr_reconciliation_backend1.service;

import com.abreha.ehr_reconciliation_backend1.dto.DataQualityValidationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.DataQualityValidationResponse;

public interface DataQualityValidationService {
    DataQualityValidationResponse validateDataQuality(
            DataQualityValidationRequest request);
}
