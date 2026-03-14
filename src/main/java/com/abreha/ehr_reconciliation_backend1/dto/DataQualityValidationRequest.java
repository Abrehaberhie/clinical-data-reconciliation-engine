package com.abreha.ehr_reconciliation_backend1.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataQualityValidationRequest {
    private Map<String, Object> demographics;
    private List<String> medications;
    private List<String> allergies;
    private List<String> conditions;
    private Map<String, Object> vitalSigns;
    private String lastUpdated;
}
