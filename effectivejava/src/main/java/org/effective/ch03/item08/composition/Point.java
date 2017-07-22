package org.effective.ch03.item08.composition;

/**
 * Created by stephen on 17/7/9.
 */
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }

    // See Item 9
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
