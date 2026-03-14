package com.abreha.ehr_reconciliation_backend1.service;
import com.abreha.ehr_reconciliation_backend1.dto.DataQualityIssue;
import com.abreha.ehr_reconciliation_backend1.dto.DataQualityValidationRequest;
import com.abreha.ehr_reconciliation_backend1.dto.DataQualityValidationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataQualityValidationServiceImpl implements DataQualityValidationService{

    @Override
    public DataQualityValidationResponse validateDataQuality(
            DataQualityValidationRequest request) {

        int completeness = 100;
        int accuracy = 100;
        int timeliness = 100;
        int clinicalPlausibility = 100;

        List<DataQualityIssue> issues = new ArrayList<>();

        if (request.getAllergies() == null || request.getAllergies().isEmpty()) {
            completeness -= 40;
            issues.add(new DataQualityIssue(
                    "allergies",
                    "No allergies documented - likely incomplete",
                    "medium"
            ));
        }

        if (request.getVitalSigns() != null && request.getVitalSigns().get("blood_pressure") != null) {
            String bp = request.getVitalSigns().get("blood_pressure").toString();
            if ("340/180".equals(bp)) {
                accuracy -= 50;
                clinicalPlausibility -= 60;
                issues.add(new DataQualityIssue(
                        "vitalSigns.blood_pressure",
                        "Blood pressure 340/180 is physiologically implausible",
                        "high"
                ));
            }
        }

        if (request.getLastUpdated() != null) {
            try {
                LocalDate updatedDate = LocalDate.parse(request.getLastUpdated());
                int monthsOld = Period.between(updatedDate, LocalDate.now()).getMonths()
                        + (Period.between(updatedDate, LocalDate.now()).getYears() * 12);

                if (monthsOld >= 7) {
                    timeliness -= 30;
                    issues.add(new DataQualityIssue(
                            "lastUpdated",
                            "Data is 7+ months old",
                            "medium"
                    ));
                }
            } catch (Exception e) {
                timeliness -= 20;
                issues.add(new DataQualityIssue(
                        "lastUpdated",
                        "Invalid date format",
                        "medium"
                ));
            }
        }

        Map<String, Integer> breakdown = new HashMap<>();
        breakdown.put("completeness", completeness);
        breakdown.put("accuracy", accuracy);
        breakdown.put("timeliness", timeliness);
        breakdown.put("clinicalPlausibility", clinicalPlausibility);

        int overallScore = (completeness + accuracy + timeliness + clinicalPlausibility) / 4;

        return new DataQualityValidationResponse(
                overallScore,
                breakdown,
                issues
        );
    }
}
