package com.ingenifi.table;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class TableParserTest {

    @Test
    void parse() {
        assertEquals(new OldTable(
                        new Cell(1, 1, "Hello"), new Cell(1, 2, "World"), new Cell(1, 3, "Row"),
                        new Cell(2, 1, "Bye"), new Cell(2, 2, "Life"), new Cell(2, 3, "!")),
                new OldTableParser().parse(
                        List.of(
                                "Hello$World$Row",
                                "Bye$Life$!"
                        )
                ));

    }

    public static class OldTableParser {
        public OldTable parse(String... lines) {
            return parse(List.of(lines));
        }

        public OldTable parse(List<String> inputLines) {
//            Table lines = Table.parseLines(inputLines);
//            return lines.table();
            return null;
        }
    }

}
