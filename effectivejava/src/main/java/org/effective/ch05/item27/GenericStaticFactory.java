package org.effective.ch05.item27;

import java.util.HashMap;

/**
 * Created by stephen on 17/7/9.
 */
public class GenericStaticFactory {
    public static <K, V> HashMap<K, V> newHashMap(){
        return new HashMap<>();
    }
}
