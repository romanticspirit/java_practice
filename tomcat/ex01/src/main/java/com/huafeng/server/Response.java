package com.huafeng.server;

import java.io.*;

/**
 “HTTP Response = Status-Line
 *(( general-header | response-header | entity-header ) CRLF)
 CRLF
 [ message-body ]
 Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF”

 */

public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response (OutputStream output){
        this.output = output;
    }

    public void setRequest (Request request){
        this.request = request;
    }

    public void sendStaticResource () throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        File file = new File (HttpServer.WEB_ROOT, request.getUri());
        if (file.exists()){
            try(FileInputStream fis = new FileInputStream(file)){
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            }
        }
        else{
            // file not found
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not found</h1>";
            output.write(errorMessage.getBytes());
        }
    }

}
