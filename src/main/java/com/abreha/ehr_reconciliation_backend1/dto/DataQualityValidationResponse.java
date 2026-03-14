package com.abreha.ehr_reconciliation_backend1.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataQualityValidationResponse {
    private Integer overallScore;
    private Map<String, Integer> breakdown;
    private List<DataQualityIssue> issuesDetected;
}
