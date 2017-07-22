package org.effective.ch02.item03.serializable;

import java.io.Serializable;

/**
 * Created by stephen on 17/7/8.
 */
public final class Elvis implements Serializable{
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    private Object readResolve() {
        // Return the one true Elvis and let the garbage collector
        // take care of the Elvis impersonator.
        return INSTANCE;
    }
}
