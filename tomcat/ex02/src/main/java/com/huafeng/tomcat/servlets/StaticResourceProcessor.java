package com.huafeng.tomcat.servlets;


import java.io.IOException;

/**
 * Created by stephen on 17/5/20.
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response){
        try{
            response.sendStaticResource();
        }
        catch (IOException e){
            e.printStackTrace();

        }
    }
}
