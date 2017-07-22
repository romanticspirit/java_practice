package org.effective.ch05.item29;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stephen on 17/7/9.
 */
public class Favorites {
    // typesafe heterogeneous container pattern
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance){
        if (type == null){
            throw new NullPointerException();
        }

        favorites.put(type, instance);
    }

    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }

}
