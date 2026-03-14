package com.abreha.ehr_reconciliation_backend1.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataQualityIssue {
    private String field;
    private String issue;
    private String severity;
}
