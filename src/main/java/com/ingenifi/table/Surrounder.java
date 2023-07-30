package com.ingenifi.table;

import org.apache.commons.lang3.StringUtils;

public class Surrounder {
    public static OpenCloseBuilder open(final String open) {
        return new OpenCloseBuilder(open);
    }

    public static WithBuilder with(final String with) {
        return new WithBuilder(with);
    }

    public static ReversingBuilder reversing(final String reversing) {
        return new ReversingBuilder(reversing);
    }

    public static class ReversingBuilder {
        private final String reversing;

        public ReversingBuilder(final String reversing) {
            this.reversing = reversing;
        }

        public String surround(final String middle) {
            return new OpenCloseBuilder(reversing).close(StringUtils.reverse(reversing)).surround(middle);
        }
    }

    public static class WithBuilder {
        private final String with;

        public WithBuilder(final String with) {
            this.with = with;
        }

        public String surround(final String middle) {
            return new OpenCloseBuilder(with).close(with).surround(middle);
        }
    }

    public static class OpenCloseBuilder {
        private final String open;
        private String close;

        public OpenCloseBuilder(final String open) {
            this.open = open;
        }

        public OpenCloseBuilder close(final String close) {
            this.close = close;
            return this;
        }

        public String surround(final String middle) {
            return open + middle + close;
        }
    }

}
