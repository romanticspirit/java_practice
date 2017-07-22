package org.effective.ch05.item27;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stephen on 17/7/9.
 */
public class Union {
    public static <E> Set<E> union(Set<E> s1, Set<E> s2){
        Set<E> results = new HashSet<>(s1);
        results.addAll(s2);
        return results;
    }
}
