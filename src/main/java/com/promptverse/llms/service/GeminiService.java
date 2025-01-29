package com.promptverse.llms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.promptverse.common.utils.LLMsURl;
import com.promptverse.llms.dto.GenerateContentRequest;
import com.promptverse.llms.dto.GenerateContentResponse;

@Service
public class GeminiService {
    private final LLMsURl llmConfig;
    private final RestTemplate restTemplate;

    public GeminiService(LLMsURl llmConfig, RestTemplate restTemplate) {
        this.llmConfig = llmConfig;
        this.restTemplate = restTemplate;
    }

    public GenerateContentResponse generateContent(String prompt) {
        String url = llmConfig.getGeminiUrl() + "?key=" + llmConfig.getGeminiAPIKey();

        GenerateContentRequest request = new GenerateContentRequest(
                List.of(new GenerateContentRequest.Content(
                        List.of(new GenerateContentRequest.Content.Part(prompt)))));

        return restTemplate.postForObject(url, request, GenerateContentResponse.class);
    }
}