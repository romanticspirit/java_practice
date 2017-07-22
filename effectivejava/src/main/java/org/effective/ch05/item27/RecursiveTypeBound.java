package org.effective.ch05.item27;

import java.util.Iterator;
import java.util.List;

/**
 * Created by stephen on 17/7/9.
 */
public class RecursiveTypeBound {
    public static <T extends Comparable<T> > T max(List<T> list){
        Iterator<T> i = list.iterator();
        T result = i.next();
        while(i.hasNext()){
            T t = i.next();
            if (t.compareTo(result) > 0){
                result = t;
            }
        }
        return result;
    }
}
