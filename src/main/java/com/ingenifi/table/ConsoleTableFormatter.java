package com.ingenifi.table;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

import static com.ingenifi.table.ListSurrounder.with;
import static java.lang.String.join;
import static java.util.stream.IntStream.rangeClosed;

@Builder
public class ConsoleTableFormatter {
    static final String NEW_LINE = "\n";
    @Builder.Default
    private RowFormatter row = new RowFormatter("|", "|");
    @Builder.Default
    private BorderFormatter boundary = new OutlineFormatter("+", "-", "+");
    @Builder.Default
    private BorderFormatter divider = new OutlineFormatter("+", "-", "+");

    public String from(final Table table) {
        return join(NEW_LINE, with(boundary.from(table)).surround(innerLines(table)));
    }

    List<String> innerLines(final Table table) {
        String dividingBorder = divider.from(table);
        List<String> lines = new ArrayList<>();
        lines.add(row.create(table, 1));
        rangeClosed(2, table.getNumberOfRows())
                .boxed()
                .map(number -> row.create(table, number))
                .forEach(formattedRow -> lines.addAll(List.of(dividingBorder, formattedRow)));
        return lines;
    }
}
