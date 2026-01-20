package was.version.servlet;

import was.httpserver.*;

import java.io.IOException;

public class HomeServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.writeBody("<h1>Library System</h1>");

        response.writeBody("<ul>");
        response.writeBody("<li><a href='/login'>로그인</a></li>");
        response.writeBody("<li><a href='/exit'>종료</a></li>");
        response.writeBody("</ul>");
    }
}
