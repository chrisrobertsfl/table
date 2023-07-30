package com.ingenifi.table;

import com.google.common.base.Splitter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

@EqualsAndHashCode
@ToString
@Builder
public class Table {

    Map<Integer, Row> rows;
    @Builder.Default
    Map<Integer, Column> columns = new HashMap<>();
    @Getter
    private int maximumNumberOfColumns;

    public static Table parse(final String fieldSeparator, List<String> lines) {
        int maximumNumberOfColumns = lines.stream()
                .mapToInt(line -> StringUtils.countMatches(line, fieldSeparator))
                .max()
                .orElse(0) + 1;
        List<List<String>> list = range(0, lines.size())
                .boxed()
                .map(index -> parseLine(lines, index, fieldSeparator))
                .toList();
        Map<Integer, Row> rows = new HashMap<>();
        rangeClosed(1, list.size())
                .boxed()
                .map(rowNumber -> Pair.of(rowNumber, createRow(list, maximumNumberOfColumns, rowNumber)))
                .forEach(pair -> rows.put(pair.getKey(), pair.getValue()));

        return Table.builder()
                .maximumNumberOfColumns(maximumNumberOfColumns)
                .rows(rows)
                .build();
    }

    static Row createRow(List<List<String>> list, int maximumNumberOfColumns, Integer rownNumber) {
        return new Row(rownNumber, rangeClosed(1, maximumNumberOfColumns)
                .boxed()
                .map(column -> createCell(list, rownNumber, column))
                .toList());
    }

    static Cell createCell(List<List<String>> list, Integer row, Integer column) {
        return new Cell(row, column, list.get(row - 1).get(column - 1));
    }

    static List<String> parseLine(List<String> lines, int index, String fieldSeparator) {
        return Splitter.on(fieldSeparator).splitToList(lines.get(index));
    }

    public Column column(int column) {
        columns.putIfAbsent(column, new Column(column, rows.values().stream()
                .filter(row -> row.cells().size() >= column)
                .map(row -> row.cells().get(column - 1))
                .toList()));
        return columns.get(column);
    }

    public Row row(int rowNumber) {
        return rows.get(rowNumber);
    }

    public int getNumberOfRows() {
        return rows.size();
    }

    public record Row(Integer rowNumber, List<Cell> cells) {

        public Cell column(Integer columnNumber) {
            return columnNumber > cells.size() ? Cell.blank(rowNumber, columnNumber) : cells.get(columnNumber - 1);
        }
    }
}
