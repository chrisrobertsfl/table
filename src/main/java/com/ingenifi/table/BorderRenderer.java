package com.ingenifi.table;

import java.util.List;

import static java.util.stream.Collectors.joining;

public record BorderRenderer(String separator, String horizontal, String corner) {

    public BorderRenderer() {
        this(Border.SEPARATOR, Border.HORIZONTAL, Border.CORNER);
    }

    public Border render(List<Integer> columnLengths) {
        return new Border(cornerColumns(generateColumns(columnLengths)), separator);
    }

    String cornerColumns(final String input) {
        return Surrounder.with(corner).surround(input);
    }

    String generateColumns(List<Integer> lengths) {
        return lengths.stream()
                .map(this::generateColumn)
                .collect(joining(corner));
    }

    String generateColumn(Integer length) {
        return horizontal.repeat(length);
    }
}

