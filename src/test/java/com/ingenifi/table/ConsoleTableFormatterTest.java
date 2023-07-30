package com.ingenifi.table;

import lombok.Builder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ingenifi.table.ListSurrounder.with;
import static java.lang.String.join;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleTableFormatterTest {

    public static final String CONSOLE_OUTPUT = """
            +------+------+---------+
            |Hello |World |Row      |
            +------+------+---------+
            |Bye   |Life  |!        |
            +------+------+---------+
            |      |      |Long one |
            +------+------+---------+
            """.trim();

    static Table simpleTable() {
        return Table.builder()
                .maximumNumberOfColumns(3)
                .rows(Map.of(
                        1, new Table.Row(1, List.of(new Cell(1, 1, "Hello"), new Cell(1, 2, "World"), new Cell(1, 3, "Row"))),
                        2, new Table.Row(2, List.of(new Cell(2, 1, "Bye"), new Cell(2, 2, "Life"), new Cell(2, 3, "!"))),
                        3, new Table.Row(3, List.of(new Cell(3, 1, ""), new Cell(3, 2, ""), new Cell(3, 3, "Long one")))))
                .build();
    }

    @Test
    void format() {
        assertEquals(CONSOLE_OUTPUT, ConsoleTableFormatter.builder()
                .row(new RowFormatter("|", "|"))
                .boundary(new OutlineFormatter("+", "-", "+"))
                .divider(new OutlineFormatter("+", "-", "+"))
                .build()
                .from(simpleTable()));
    }

    @Builder
    public static class ConsoleTableFormatter {
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

}
