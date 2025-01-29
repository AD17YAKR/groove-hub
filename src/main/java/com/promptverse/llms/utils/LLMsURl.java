package com.promptverse.llms.utils;

import org.springframework.stereotype.Component;
import io.github.cdimascio.dotenv.Dotenv;

@Component
public class LLMsURl {
    private final String geminiAPIKey;
    private final String geminiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

    public LLMsURl(Dotenv dotenv) {
        this.geminiAPIKey = dotenv.get("GEMINI_API_KEY");
    }

    public String getGeminiAPIKey() {
        return geminiAPIKey;
    }

    public String getGeminiUrl() {
        return geminiUrl;
    }
}
