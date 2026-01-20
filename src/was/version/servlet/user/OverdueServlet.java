package was.version.servlet.user;

import Library.LibraryService;
import model.*;
import was.httpserver.*;

import java.util.List;

public class OverdueServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        ServletManager manager = request.getServletManager();

        LibraryService libraryService = manager.getLibraryService();
        SessionManager sessionManager = manager.getSessionManager();

        String sid = request.getParameter("sessionId");
        User user = sessionManager.getUser(sid);

        List<BorrowRecord> overdueList = libraryService.getOverdueBooks(user);

        response.writeBody("<h2>연체 도서 목록</h2>");
        response.writeBody("<ul>");

        for (BorrowRecord record : overdueList) {
            Book book = record.getBook();
            response.writeBody("<li>");
            response.writeBody(book.getTitle() + " - " + book.getAuthor() +
                    " (대여일: " + record.getBorrowDate() + ")");
            response.writeBody("</li>");
        }

        response.writeBody("</ul>");
        response.writeBody("<a href='/user?sessionId=" + sid + "'>뒤로가기</a>");
    }
}
