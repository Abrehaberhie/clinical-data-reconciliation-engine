package com.abreha.ehr_reconciliation_backend1.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationSourceRecord {
    private String system;
    private String medication;
    private String lastUpdated;
    private String lastFilled;
    private String sourceReliability;
}
