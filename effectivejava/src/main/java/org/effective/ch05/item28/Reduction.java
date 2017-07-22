package org.effective.ch05.item28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stephen on 17/7/9.
 */
public class Reduction {
    // Wildcard type for parameter that serves as an E producer
    static <E> E reduce(List<? extends E> list, Function<E> f, E initVal) {
        List<E> snapshot;
        synchronized (list) {
            snapshot = new ArrayList<E>(list);
        }
        E result = initVal;
        for (E e : snapshot)
            result = f.apply(result, e);
        return result;
    }

    private static final Function<Number> MAX = new Function<Number>() {
        public Number apply(Number n1, Number n2) {
            return Double.compare(n1.doubleValue(), n2.doubleValue()) > 0 ? n1
                    : n2;
        }
    };
}
