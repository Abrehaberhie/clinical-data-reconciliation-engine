package com.abreha.ehr_reconciliation_backend1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationReconciliationRequest {

    private PatientContext patientContext;
    private List<MedicationSourceRecord> sources;

}
