package com.ingenifi.table;

import java.util.List;

public record Column(int number, List<Cell> cells) {
    public int maximumWidth() {
        return cells.stream()
                .map(Cell::contents)
                .map(String::length)
                .max(Integer::compareTo)
                .orElse(0);
    }
}
