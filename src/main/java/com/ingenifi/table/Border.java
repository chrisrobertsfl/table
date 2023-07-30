package com.ingenifi.table;

public record Border(String above, String between, String below) {
    public static final String CORNER = "+";
    public static final String HORIZONTAL = "-";
    public static final String SEPARATOR = "\n";
    static final String VERTICAL = "|";

    public Border(String between, String separator) {
        this(between + separator, between, separator + between);
    }
}
