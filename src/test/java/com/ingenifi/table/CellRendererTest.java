package com.ingenifi.table;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CellRendererTest {

    static Stream<Arguments> cellTestCases() {
        return Stream.of(
                Arguments.of("a     ", "a"),
                Arguments.of("a     ", "a "),
                Arguments.of("a     ", "a             "),
                Arguments.of("      ", ""),
                Arguments.of("      ", null)
        );
    }

    static String bar(final String s) {
        return "->|" + s + "|<-";
    }

    static void assertEqualsWithDelimiters(String rendered, String input) {
        assertEquals(bar(rendered), bar(input));
    }

    @ParameterizedTest(name = "When rendering Cell contents ->|{1}|<- result should be ->|{0}|<-")
    @MethodSource("cellTestCases")
    void cellRendering(String rendered, String input) {
        assertEqualsWithDelimiters(rendered, new CellRenderer(6).render(input));
    }

    @ParameterizedTest(name = "When rendering a Cell, maxLength of {0} should raise exception")
    @CsvSource(value = {"-1", "0", "1"})
    void cellRenderingWithMaxLengthLessThanTwo(int maxLength) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CellRenderer(maxLength).render(null));
        assertEquals("maxLength must be greater than 1", exception.getMessage());
    }

    @DisplayName("When rendering a Cell, the trimmed content's length cannot be bigger than the maxLength")
    @Test
    void cellRenderingWithTrimmedContentsLengthBiggerThanMaxLength() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CellRenderer(3).render("abcdefg"));
        assertEquals("Trimmed contents must be no bigger than maxLength", exception.getMessage());
    }
}
