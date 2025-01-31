package com.promptverse.llms.dto;

import java.util.List;

public record GenerateContentResponse(List<Candidate> candidates) {
    public record Candidate(Content content, String finishReason) {
        public record Content(List<Part> parts) {
            public record Part(String text) {
            }
        }
    }
}