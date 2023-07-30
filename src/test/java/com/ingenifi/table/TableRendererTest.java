package com.ingenifi.table;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableRendererTest {

    public static String surround(final String open, final String target, final String close) {
        return open + target + close;
    }

    @Test
    void renderTableWithOneRow() {
        BorderRenderer borderRenderer = new BorderRenderer("\n", "-", "+");
        TableRenderer tableRenderer = new TableRenderer(borderRenderer, new RowRenderer(), new ColumnsParser(), new ColumnsCalculator());
        assertEquals(
                """
                        +------+------+----+
                        |Hello |World |Row |
                        +------+------+----+
                        |Bye   |Life  |!   |
                        +------+------+----+
                        """.trim(),
                tableRenderer.render(List.of("Hello$World$Row", "Bye$Life$!")));
    }

}
