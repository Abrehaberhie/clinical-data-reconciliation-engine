package com.abreha.ehr_reconciliation_backend1.dto;

import com.abreha.ehr_reconciliation_backend1.model.ConflictItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReconciliationResponse {

    private String patientId;
    private List<ConflictItem> conflicts;
    private String status;

}
