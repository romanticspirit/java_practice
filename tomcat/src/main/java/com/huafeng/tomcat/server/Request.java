package com.huafeng.tomcat.server;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by stephen on 17/5/20.
 */
public class Request {
    private InputStream input;
    private String uri;
    public  Request (InputStream input){
        this.input = input;
    }

    public void parse (){
        // read a set of characters
        StringBuffer request = new StringBuffer(2048);
        int i =0 ;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; ++j){
            request.append((char) buffer[j]);
        }

        uri = parseUri(request.toString());
    }

    private String parseUri (String requestString){
        //An Http request consist of three components
        /*
        Method—Uniform Resource Identifier (URI)—Protocol/Version
        Request headers
        Entity body

        “Each header is separated by a carriage return/linefeed (CRLF) sequence”

        example :
        “POST /examples/default.jsp HTTP/1.1
        Accept: text/plain; text/html Accept-Language: en-gb Connection: Keep-Alive
        Host: localhost
        User-Agent: Mozilla/4.0 (compatible; MSIE 4.01; Windows 98) Content-Length: 33
        Content-Type: application/x-www-form-urlencoded Accept-Encoding: gzip, deflate
        lastName=Franks&firstName=Michael”

         */
        //“GET /index.html HTTP/1.1”
        //“POST /examples/default.jsp HTTP/1.1”
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1){
            index2 = requestString.indexOf(' ', index1 + 1);

            if (index2 > index1){
                return requestString.substring(index1+1, index2);
            }
        }
        return null;
    }

    public String getUri (){
        return uri;
    }
}
