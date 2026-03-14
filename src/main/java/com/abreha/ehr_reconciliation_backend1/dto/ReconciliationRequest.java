package com.abreha.ehr_reconciliation_backend1.dto;

import java.util.List;

public class ReconciliationRequest {

    private String patientId;
    private List<PatientRecordRequest> records;

    public ReconciliationRequest() {
    }

    public ReconciliationRequest(String patientId, List<PatientRecordRequest> records) {
        this.patientId = patientId;
        this.records = records;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public List<PatientRecordRequest> getRecords() {
        return records;
    }

    public void setRecords(List<PatientRecordRequest> records) {
        this.records = records;
    }
}
