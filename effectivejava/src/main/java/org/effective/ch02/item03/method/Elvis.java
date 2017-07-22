package org.effective.ch02.item03.method;

/**
 * Created by stephen on 17/7/8.
 */

//page 17
public class Elvis {

    private static final Elvis INSTANCE = new Elvis();

    private Elvis(){

    }

    public static Elvis getInstance () {
        return INSTANCE;
    }
}
