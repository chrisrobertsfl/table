package com.ingenifi.table;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;

public record OutlineFormatter(String outerEdgeSymbol, String innerEdgeSymbol,
                               String columnSeparatorSymbol) implements BorderFormatter {
    @Override
    public String from(final Table table) {
        return Surrounder.with(outerEdgeSymbol).surround(rowSeparator(table));
    }

    String rowSeparator(Table table) {
        return rangeClosed(1, table.getMaximumNumberOfColumns())
                .boxed()
                .map(columnNumber -> columnBorder(table, columnNumber))
                .collect(joining(columnSeparatorSymbol));
    }

    String columnBorder(Table table, int column) {
        return innerEdgeSymbol.repeat(table.column(column).maximumWidth() + 1);
    }
}
