package org.effective.ch05.item27;

/**
 * Created by stephen on 17/7/9.
 */
public class GenericSingletonFactory {
    // Generic singleton factory pattern
    private static UnaryFunction<Object> IDENTITY_FUNCTION = new UnaryFunction<Object>() {
        public Object apply(Object arg) {
            return arg;
        }
    };

    // IDENTITY_FUNCTION is stateless and its type parameter is
    // unbounded so it's safe to share one instance across all types.
    @SuppressWarnings("unchecked")
    public static <T> UnaryFunction<T> identityFunction() {
        return (UnaryFunction<T>) IDENTITY_FUNCTION;
    }

}
