package com.ingenifi.table;

import java.util.List;

public record Columns(List<String> fields) {
    public int numberOfColumns() {
        return fields.size();
    }

    public int length(int position) {
        return fields.get(position).length();
    }
}
