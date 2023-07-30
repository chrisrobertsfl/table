package com.ingenifi.table;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorderRendererTest {

    @Test
    void renderBorderGivenDimensions() {
        assertEquals("+------+------+----+", new BorderRenderer().render(List.of(6, 6, 4)).between());
    }

}
