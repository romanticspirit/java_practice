package com.huafeng.tomcat.servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by stephen on 17/5/20.
 */
public class ServletProcessor1 {
    public void process (Request request, Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try{
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File (Constants.WEB_ROOT);

            String repository = (new URL("file", null, classPath.getCanonicalPath()+File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);

        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Class myclass = null;

        try{
            myclass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }

        Servlet servlet = null;
        try{
            servlet = (Servlet) myclass.newInstance();
            servlet.service(new RequestFacade(request), new ResponseFacade(response));
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (Throwable e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
