package org.effective.ch05.item29;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * Created by stephen on 17/7/9.
 */
public class PrintAnnotation {
    static Annotation getAnnotation(AnnotatedElement element, String annotationName){
        Class<?> annotationType  =null;
        try{
            annotationType = Class.forName(annotationName);
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }

        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }
}
