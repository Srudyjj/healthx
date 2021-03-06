package com.example.mock_client.services;

import com.example.mock_client.model.HealthAdvice;
import com.example.mock_client.model.UserHealthData;
import com.example.mock_client.proxy.HealthSystemProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthAdviceService {

    private final HealthSystemProxy healthSystemProxy;

    public HealthAdviceService(HealthSystemProxy healthSystemProxy) {
        this.healthSystemProxy = healthSystemProxy;
    }

    public void generateHealthAdvices(List<UserHealthData> userHealthData) {
        userHealthData.forEach(u -> {
            HealthAdvice advice = getMockHealthAdvice(u.getUsername());
            healthSystemProxy.sendAdvice(List.of(advice));
        });
    }

    private HealthAdvice getMockHealthAdvice(String username) {
        HealthAdvice advice = new HealthAdvice();
        advice.setUsername(username);
        advice.setAdvice("Mock advice for " + username);
        return advice;
    }
}
