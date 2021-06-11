package com.example.mock_client.controllers;

import com.example.mock_client.model.UserHealthData;
import com.example.mock_client.services.HealthAdviceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class HealthAdviceController {
    private final HealthAdviceService adviceService;

    public HealthAdviceController(HealthAdviceService adviceService) {
        this.adviceService = adviceService;
    }

    @PostMapping("/data")
    public void collectHealthDataForAdvice(@RequestBody List<UserHealthData> userHealthData) {
        adviceService.generateHealthAdvices(userHealthData);
    }
}
