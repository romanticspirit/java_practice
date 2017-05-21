package com.huafeng.tomcat.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by stephen on 17/5/20.
 */
public class HttpServer1 {
    private static final String SHUTDOWN_COMMAND="/SHUTDOWN";

    private boolean shutdown = false;

    public static void main (String args[]){
        HttpServer1 server = new HttpServer1();
        server.await();
    }

    public void await (){
        ServerSocket serverSocket = null;
        int port = 8080;
        try{
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        while(!shutdown){
            InputStream input =null;
            OutputStream output = null;
            try(Socket socket = serverSocket.accept()){
                input = socket.getInputStream();
                output = socket.getOutputStream();
                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                //response.sendStaticResource();

                if (request.getUri().startsWith("/servlet")){
                    ServletProcessor1 processor1 = new ServletProcessor1();
                    processor1.process(request, response);
                   // Serlet
                }
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
}
