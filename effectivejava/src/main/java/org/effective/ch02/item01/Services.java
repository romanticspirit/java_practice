package org.effective.ch02.item01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by stephen on 17/7/8.
 */
public class Services {
    private Services() {

    }

    private static final Map<String, Provider> providers = new ConcurrentHashMap<>();
    public static final String DEFUALT_PROVIDER_NAME="<dev>";

    public static void registerDefaultProvider (Provider p) {
        registerProvider(DEFUALT_PROVIDER_NAME, p);
    }

    public static void registerProvider (String name, Provider p){
        providers.put(name, p);
    }

    public static Service newInstance(){
       return newInstance(DEFUALT_PROVIDER_NAME);
    }

    public static Service newInstance(String name){
        Provider provider =  providers.get(name);

        if (provider == null) {
            throw new IllegalArgumentException("No Proivder registered with name:"+name);
        }

        return provider.newService();
    }

}
