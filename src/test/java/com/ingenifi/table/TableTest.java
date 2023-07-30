package com.ingenifi.table;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableTest {

    @Test
    void parse() {
        Table expected = Table.builder()
                .maximumNumberOfColumns(3)
                .rows(Map.of(
                        1, new Table.Row(1, List.of(new Cell(1, 1, "Hello"), new Cell(1, 2, "World"), new Cell(1, 3, "Row"))),
                        2, new Table.Row(2,List.of(new Cell(2, 1, "Bye"), new Cell(2, 2, "Life"), new Cell(2, 3, "!"))),
                        3, new Table.Row(3,List.of(new Cell(3, 1, ""), new Cell(3, 2, ""), new Cell(3, 3, "Long one")))))
                .build();
        Table actual = Table.parse("$", List.of("Hello$World$Row", "Bye$Life$!", "$$Long one"));
        assertEquals(expected, actual);
        assertEquals(5, actual.column(1).maximumWidth());
        assertEquals(5, actual.column(2).maximumWidth());
        assertEquals(8, actual.column(3).maximumWidth());
    }
}
