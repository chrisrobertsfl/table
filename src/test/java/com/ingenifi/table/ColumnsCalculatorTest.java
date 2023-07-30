package com.ingenifi.table;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColumnsCalculatorTest {


    @Test
    void calculateAllMaxColumnLengths() {
        assertEquals(List.of(6, 7, 4), new ColumnsCalculator().maxLengths(List.of(
                new Columns(List.of("123456", "12345", "1")),
                new Columns(List.of("1234", "1234567", "12")),
                new Columns(List.of("123", "1234", "1234")))));
    }

}
