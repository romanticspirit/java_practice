package com.huafeng.tomcat.servlets;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Created by stephen on 17/5/20.
 */
public class Request implements ServletRequest {
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

    @Override
    public Object getAttribute(String name) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public long getContentLengthLong() {
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getParameter(String name) {
        return null;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return null;
    }

    @Override
    public String[] getParameterValues(String name) {
        return new String[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return null;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String name, Object o) {

    }

    @Override
    public void removeAttribute(String name) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    @Override
    public String getRealPath(String path) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }
}
