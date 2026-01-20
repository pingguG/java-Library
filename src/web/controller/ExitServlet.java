package web.controller;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

public class ExitServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>Goodbye!</h1>");
        response.writeBody("<a href='/'>home</a>");
    }
}
