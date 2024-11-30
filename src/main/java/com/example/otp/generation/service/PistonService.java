package com.example.otp.generation.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PistonService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String PISTON_API_URL = "https://emkc.org/api/v2/piston/execute";

    public Map<String, Object> executeCode(String language, String version, String code, String input, String expectedOutput) {
        // Create payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("language", language);
        payload.put("version", version);
        payload.put("files", new Object[]{ Map.of("name", "main", "content", code) });
        payload.put("stdin", input);

        // Call Piston API
        Map<String, Object> response = restTemplate.postForObject(PISTON_API_URL, payload, Map.class);

        // Parse the result
        Map<String, Object> runResult = (Map<String, Object>) response.get("run");
        String actualOutput = (String) runResult.get("stdout");
        boolean passed = actualOutput.trim().equals(expectedOutput.trim());

        // Prepare and return result
        Map<String, Object> result = new HashMap<>();
        result.put("input", input);
        result.put("expectedOutput", expectedOutput);
        result.put("actualOutput", actualOutput);
        result.put("passed", passed);

        return result;
    }
}
