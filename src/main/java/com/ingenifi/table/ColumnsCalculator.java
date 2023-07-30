package com.ingenifi.table;

import java.util.List;

import static java.util.stream.IntStream.range;

public record ColumnsCalculator() {
     int maxLength(List<Columns> columnsList, int position) {
        return columnsList.stream()
                .filter(columns -> columns.numberOfColumns() >= position + 1)
                .map(columns -> columns.length(position))
                .max(Integer::compare)
                .orElse(0);
    }

    public List<Integer> maxLengths(List<Columns> columnsList) {
        return range(0, columnsList.get(0).numberOfColumns())
                .boxed()
                .map(position -> maxLength(columnsList, position))
                .toList();
    }
}
