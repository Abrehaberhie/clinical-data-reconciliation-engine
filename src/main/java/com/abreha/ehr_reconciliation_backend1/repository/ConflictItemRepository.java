package com.abreha.ehr_reconciliation_backend1.repository;

import com.abreha.ehr_reconciliation_backend1.model.ConflictItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConflictItemRepository extends JpaRepository<ConflictItem,  Long> {
    List<ConflictItem> findByPatientId(String patientId);
}
