package com.ingenifi.table;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColumnsParserTest {

    public static final Columns COLUMNS = new Columns(List.of("Hello", "World", "Row"));

    @Test
    void parse() {
        assertEquals(COLUMNS, new ColumnsParser("$").parse("Hello$World$Row"));
    }

    @Test
    void numberOfColumns() {
        assertEquals(3, COLUMNS.numberOfColumns());
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 5", "1, 5", "2, 3"})
    void lengthOfColumns(int position, int length) {
        assertEquals(length, COLUMNS.length(position));
    }

}
