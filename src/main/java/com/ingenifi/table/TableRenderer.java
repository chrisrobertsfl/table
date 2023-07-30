package com.ingenifi.table;

import java.util.List;

import static java.util.stream.Collectors.joining;

public record TableRenderer(BorderRenderer borderRenderer, RowRenderer rowRenderer, ColumnsParser columnsParser,
                            ColumnsCalculator columnsCalculator) {
    public String render(final List<String> lines) {
        List<Integer> columnLengths = determineColumnLengths(lines);
        Border border = borderRenderer.render(columnLengths);
        String rows = generateRows(lines, columnLengths, border);
        return rows + border.below();
    }

    private String generateRows(List<String> lines, List<Integer> columnLengths, Border border) {
        return lines.stream()
                .map(line -> rowRenderer.render(columnLengths, line))
                .map(row -> border.above() + row)
                .collect(joining("\n"));
    }

    // TODO:  You will need to determine the max number of fields in each and then adjust the other columns that have less to match -> normalize the columns with a normalizer
    List<Integer> determineColumnLengths(List<String> lines) {
        List<Columns> columnsList = lines.stream()
                .map(columnsParser::parse)
                .toList();
        List<Integer> integers = columnsCalculator.maxLengths(columnsList);
        return integers.stream().map(n -> n + 1).toList();
    }
}
