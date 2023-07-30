package com.ingenifi.table;

import com.google.common.base.Splitter;

public record ColumnsParser(String fieldSeparator) {

    public static final String FIELD_SEPARATOR = "$";

    public ColumnsParser() {
        this(FIELD_SEPARATOR);
    }

    public Columns parse(String input) {
        return new Columns(Splitter.on(fieldSeparator).splitToList(input));
    }
}
