package com.promptverse.llms.dto;

import java.util.List;

public record GenerateContentRequest(List<Content> contents) {
    public record Content(List<Part> parts) {
        public record Part(String text) {
        }
    }
}
