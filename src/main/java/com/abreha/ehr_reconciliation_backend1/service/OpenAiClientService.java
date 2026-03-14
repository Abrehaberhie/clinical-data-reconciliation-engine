package com.abreha.ehr_reconciliation_backend1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


import java.util.HashMap;
import java.util.Map;

@Service


public class OpenAiClientService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    public OpenAiClientService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String generateMedicationReasoning(String prompt) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("model", model);
            body.put("input", prompt);

            Map<String, Object> textFormat = new HashMap<>();
            textFormat.put("type", "text");

            Map<String, Object> textObj = new HashMap<>();
            textObj.put("format", textFormat);

            body.put("text", textObj);

            String response = restClient.post()
                    .uri("/responses")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .body(body)
                    .retrieve()
                    .body(String.class);

            JsonNode root = objectMapper.readTree(response);

            if (root.has("output_text")) {
                return root.get("output_text").asText();
            }

            return "No reasoning returned by OpenAI.";
        }
        //catch (Exception e) {
           // return "OpenAI call failed: " + e.getMessage();
        //}

        catch (Exception e) {
            return "AI reasoning is unavailable, so the system used rule-based reconciliation based on source reliability, recency, and medication history.";
        }
    }
}
