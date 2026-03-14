package com.abreha.ehr_reconciliation_backend1.service;
import com.abreha.ehr_reconciliation_backend1.dto.PatientRecordRequest;
import com.abreha.ehr_reconciliation_backend1.dto.ReconciliationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.ReconciliationResponse;
import com.abreha.ehr_reconciliation_backend1.model.ConflictItem;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReconciliationServiceImpl implements ReconciliationService{

    @Override
    public ReconciliationResponse reconcileRecords(ReconciliationRequest request) {

        List<ConflictItem> conflictItems = new ArrayList<>();
        Map<String, String> fieldValueMap = new HashMap<>();

        for (PatientRecordRequest record : request.getRecords()) {
            String fieldName = record.getFieldName();
            String fieldValue = record.getFieldValue();

            ConflictItem item = new ConflictItem(
                    null,
                    request.getPatientId(),
                    fieldName,
                    record.getSource(),
                    fieldValue,
                    false
            );

            if (fieldValueMap.containsKey(fieldName)
                    && !fieldValueMap.get(fieldName).equals(fieldValue)) {

                item.setConflict(true);

                for (ConflictItem existingItem : conflictItems) {
                    if (existingItem.getFieldName().equals(fieldName)) {
                        existingItem.setConflict(true);
                    }
                }
            } else {
                fieldValueMap.put(fieldName, fieldValue);
            }

            conflictItems.add(item);
        }

        String status = conflictItems.stream()
                .anyMatch(ConflictItem::isConflict)
                ? "CONFLICT_FOUND"
                : "NO_CONFLICT";

        return new ReconciliationResponse(
                request.getPatientId(),
                conflictItems,
                status
        );
    }
}
