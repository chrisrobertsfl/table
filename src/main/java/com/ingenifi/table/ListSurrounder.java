package com.ingenifi.table;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ListSurrounder {


    public static <T> WithListBuilder<T> with(final T with) {
        return new WithListBuilder<>(with);
    }

    public static <T> OpenCloseListBuilder<T> open(final T open) {
        return new OpenCloseListBuilder<>(open);
    }

    public static class WithListBuilder<T> {
        private final T with;

        public WithListBuilder(final T with) {
            this.with = with;
        }

        public List<T> surround(final List<T> middle) {
            return new OpenCloseListBuilder<T>(with).close(with).surround(middle);
        }
    }

    public static class OpenCloseListBuilder<T> {
        private final T open;
        private T close;

        public OpenCloseListBuilder(final T open) {
            this.open = open;
        }

        public OpenCloseListBuilder<T> close(final T close) {
            this.close = close;
            return this;
        }

        public List<T> surround(final List<T> middle) {
            List<T> instance = new ArrayList<>();
            instance.add(open);
            instance.addAll(middle);
            instance.add(close);
            return instance;
        }
    }

}
