package org.effective.ch03.item08;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by stephen on 17/7/9.
 */
public class CounterPoint  extends Point{
    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public int numberCreated() {
        return counter.get();
    }

}
