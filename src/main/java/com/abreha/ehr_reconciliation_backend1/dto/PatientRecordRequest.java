package com.abreha.ehr_reconciliation_backend1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientRecordRequest {
    @NotBlank
    private String source;

    @NotBlank
    private String fieldName;

    @NotBlank
    private String fieldValue;

}
