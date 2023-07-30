package com.ingenifi.table;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;
import static org.apache.commons.lang3.StringUtils.rightPad;

public record RowFormatter(String outerEdgeSymbol, String columnSeparatorSymbol)  {
    public String create(final Table table, int rowNumber) {
        return Surrounder.with(outerEdgeSymbol).surround(rowContent(table, rowNumber));
    }

    String rowContent(Table table, int rowNumber) {
        return rangeClosed(1, table.getMaximumNumberOfColumns())
                .boxed()
                .map(columnNumber -> rightPad(table.row(rowNumber).column(columnNumber).contents(), table.column(columnNumber).maximumWidth() + 1))
                .collect(joining(columnSeparatorSymbol));
    }
}
