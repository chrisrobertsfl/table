package com.ingenifi.table;

import java.util.List;

public record OldTable(List<Cell> cells) {
    public OldTable(Cell... cells) {
        this(List.of(cells));
    }
}
