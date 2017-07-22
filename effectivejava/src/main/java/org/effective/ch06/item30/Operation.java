package org.effective.ch06.item30;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stephen on 17/7/9.
 */
public enum Operation {
    PLUS("+"){
        double apply (double x, double y){
            return x + y;
        }
    },

    MINUS("-") {
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }


    // Implementing a fromString method on an enum type - Page 154
    private static final Map<String, Operation> stringToEnum = new HashMap<String, Operation>();
    static { // Initialize map from constant name to enum constant
        for (Operation op : values())
            stringToEnum.put(op.toString(), op);
    }

    // Returns Operation for string, or null if string is invalid
    public static Operation fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    abstract double apply(double x, double y);
}
