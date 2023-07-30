package com.ingenifi.table;

public record Cell(int row, int column, String contents) {
    public static Cell blank(Integer rowNumber, Integer columnNumber) {
        return new Cell(rowNumber, columnNumber, "");
    }
}
