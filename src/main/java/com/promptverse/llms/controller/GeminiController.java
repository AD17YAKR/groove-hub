package com.promptverse.llms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.promptverse.llms.dto.GenerateContentResponse;
import com.promptverse.llms.service.GeminiService;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {
    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/generate")
    public ResponseEntity<GenerateContentResponse> generateContent(@RequestBody String prompt) {
        return ResponseEntity.ok(geminiService.generateContent(prompt));
    }
}