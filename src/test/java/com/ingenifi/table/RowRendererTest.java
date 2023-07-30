package com.ingenifi.table;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RowRendererTest {

    @Test
    void renderARow() {
        assertEquals("|Hello |World |Row |", new RowRenderer().render(List.of(6, 6, 4), "Hello$World$Row"));
    }

    @Test
    void unequalColumnsRaisesException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RowRenderer().render(List.of(1, 2, 3), "a"));
        assertEquals("Unequal number of column lengths compared to input column content", exception.getMessage());
    }

}
