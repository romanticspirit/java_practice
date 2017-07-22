package org.effective.ch02.item03.enumeration;

/**
 * Created by stephen on 17/7/8.
 */

// serialization for free
//    a single-element enum type is the best way to implement a singleton
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding(){
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
