package com.ingenifi.table;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public record CellRenderer(int maxLength) {
    public CellRenderer {
        assertMaxLengthGreaterThanOne(maxLength);
    }

    void assertMaxLengthGreaterThanOne(int maxLength) {
        Optional.of(maxLength)
                .filter(length -> length > 1)
                .orElseThrow(() -> new IllegalArgumentException("maxLength must be greater than 1"));
    }

    public String render(String input) {
        String trimmed = trimContents(input);
        assertTrimmedContentsNoBiggerThanMaxLength(trimmed);
        return trimmed + generateFiller(trimmed);
    }

    void assertTrimmedContentsNoBiggerThanMaxLength(String trimmed) {
        if (trimmed.length() > maxLength) {
            throw new IllegalArgumentException("Trimmed contents must be no bigger than maxLength");
        }
    }

    String trimContents(final String contents) {
        return ofNullable(contents).orElse("").trim();
    }

    String generateFiller(String trimmed) {
        return " ".repeat(maxLength - trimmed.length());
    }
}
