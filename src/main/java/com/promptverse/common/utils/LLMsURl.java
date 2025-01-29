package com.promptverse.common.utils;

import org.springframework.stereotype.Component;

@Component
public class LLMsURl {
    private final String geminiAPIKey = "AIzaSyATKlsOtHkOQTLjqeFbq8Y49Js528uzcIo";
    private final String geminiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

    public String getGeminiAPIKey() {
        return geminiAPIKey;
    }

    public String getGeminiUrl() {
        return geminiUrl;
    }
}