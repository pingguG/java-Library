package was.version.servlet.admin;

import was.httpserver.*;

import java.io.IOException;

public class AdminServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {

        String sid = request.getParameter("sessionId");

        response.writeBody("<h1>Admin 메뉴</h1>");

        response.writeBody("<ul>");
        response.writeBody("<li><a href='/addBook?sessionId=" + sid + "'>책 추가</a></li>");
        response.writeBody("<li><a href='/addUser?sessionId=" + sid + "'>유저 추가</a></li>");
        response.writeBody("<li><a href='/'>홈으로</a></li>");
        response.writeBody("</ul>");
    }
}
