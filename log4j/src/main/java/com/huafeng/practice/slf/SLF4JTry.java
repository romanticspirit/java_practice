package com.huafeng.practice.slf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by i067561 on 05/05/2017.
 */
public class SLF4JTry {
    private static Logger logger = LoggerFactory.getLogger(SLF4JTry.class);

    public static void main (String args[]){
        logger.info("hello world");
    }
}
