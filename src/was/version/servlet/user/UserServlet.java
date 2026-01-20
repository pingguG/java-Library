package was.version.servlet.user;

import was.httpserver.*;
import java.io.IOException;

public class UserServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String sid = request.getParameter("sessionId");

        response.writeBody("<h2>사용자 메뉴</h2>");

        response.writeBody("<ul>");
        response.writeBody("<li><a href='/bookList?sessionId=" + sid + "'>도서 목록</a></li>");
        response.writeBody("<li><a href='/borrowBook?sessionId=" + sid + "'>도서 대출</a></li>");
        response.writeBody("<li><a href='/returnBook?sessionId=" + sid + "'>도서 반납</a></li>");
        response.writeBody("<li><a href='/Overdue?sessionId=" + sid + "'>내 대출 목록</a></li>");
        response.writeBody("<li><a href='/'>홈으로</a></li>");
        response.writeBody("</ul>");
    }
}
