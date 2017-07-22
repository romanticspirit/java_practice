package org.effective.ch05;

import java.util.List;

/**
 * Created by stephen on 17/7/9.
 */
public class Dummy {
    public static void main(String[] args) {
        List<?> array[] = new List<?>[1];

        // wont compile List<String> array[] = new List<String>[1];
    }
}
