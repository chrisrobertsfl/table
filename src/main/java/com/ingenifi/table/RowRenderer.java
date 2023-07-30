package com.ingenifi.table;

import com.google.common.base.Splitter;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public record RowRenderer(String fieldSeparator) {
    public RowRenderer() {this(ColumnsParser.FIELD_SEPARATOR);}

    // TODO:  Columnns Parser already called! I think we just need the columns
    public String render(List<Integer> columnLengths, String input) {
        List<String> contents = Splitter.on(fieldSeparator).splitToList(input);
        assertEqualNumberOfColumns(columnLengths, contents);
        String body = generateRow(columnLengths, contents);
        return Surrounder.with(Border.VERTICAL).surround(body);
    }

    // TODO:  Increase performance by introducing a cell renderer cache
    String generateRow(List<Integer> columnLengths, List<String> contents) {
        return range(0, columnLengths.size())
                .boxed()
                .map(position -> new CellRenderer(columnLengths.get(position)).render(contents.get(position)))
                .collect(joining(Border.VERTICAL));
    }

    void assertEqualNumberOfColumns(List<Integer> columnLengths, List<String> contents) {
        if (columnLengths.size() != contents.size()) {
            throw new IllegalArgumentException("Unequal number of column lengths compared to input column content");
        }
    }
}
