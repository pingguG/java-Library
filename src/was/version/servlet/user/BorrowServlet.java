package was.version.servlet.user;

import Library.LibraryService;
import model.*;
import was.httpserver.*;

import java.io.IOException;
import java.util.List;

public class BorrowServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        ServletManager manager = request.getServletManager();

        LibraryService libraryService = manager.getLibraryService();
        SessionManager sessionManager = manager.getSessionManager();

        String sid = request.getParameter("sessionId");
        String title = request.getParameter("title");

        User user = sessionManager.getUser(sid);

        if (title == null) {
            List<Book> books = libraryService.getAvailableBooks();

            response.writeBody("<h2>대출 가능한 도서 목록</h2>");
            response.writeBody("<ul>");

            for (Book book : books) {
                response.writeBody("<li>");
                response.writeBody(book.getTitle() + " - " + book.getAuthor());
                response.writeBody(" <a href='/borrowBook?sessionId=" + sid
                        + "&title=" + book.getTitle() + "'>대출</a>");
                response.writeBody("</li>");
            }

            response.writeBody("</ul>");
            response.writeBody("<a href='/user?sessionId=" + sid + "'>뒤로가기</a>");
            return;
        }

        boolean result = libraryService.borrowBook(user, title);

        if (result) {
            response.writeBody("<p>" + title + " 대출 성공!</p>");
        } else {
            response.writeBody("<p>대출 실패!</p>");
        }

        response.writeBody("<a href='/borrowBook?sessionId=" + sid + "'>다른 책 빌리기</a><br>");
        response.writeBody("<a href='/user?sessionId=" + sid + "'>사용자 메뉴로</a>");
    }
}
