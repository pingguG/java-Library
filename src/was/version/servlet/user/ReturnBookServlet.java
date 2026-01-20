package was.version.servlet.user;

import Library.LibraryService;
import was.httpserver.*;
import model.*;

import java.io.IOException;
import java.util.List;

public class ReturnBookServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        ServletManager manager = request.getServletManager();

        LibraryService libraryService = manager.getLibraryService();
        SessionManager sessionManager = manager.getSessionManager();

        String sid = request.getParameter("sessionId");
        String title = request.getParameter("title");

        User user = sessionManager.getUser(sid);
        if (title == null) {
            List<Book> books = libraryService.getBorrowedBooks(user);

            response.writeBody("<h2>반납할 책을 선택하세요</h2>");
            response.writeBody("<ul>");

            for (Book book : books) {
                response.writeBody("<li>");
                response.writeBody(book.getTitle() + " - " + book.getAuthor());
                response.writeBody(" <a href='/returnBook?sessionId=" + sid
                        + "&title=" + book.getTitle() + "'>반납</a>");
                response.writeBody("</li>");
            }

            response.writeBody("</ul>");
            response.writeBody("<a href='/user?sessionId=" + sid + "'>뒤로가기</a>");
            return;
        }

        boolean result = libraryService.returnBook(user, title);

        if (result) {
            response.writeBody("<p>" + title + " 반납 완료!</p>");
        } else {
            response.writeBody("<p>반납 실패!</p>");
        }

        response.writeBody("<a href='/user?sessionId=" + sid + "'>돌아가기</a>");
    }
}
