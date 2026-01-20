package was.version.servlet.user;

import Library.LibraryService;
import model.*;
import was.httpserver.*;

import java.util.List;

public class BookListServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {

        ServletManager manager = request.getServletManager();
        LibraryService libraryService = manager.getLibraryService();

        String sid = request.getParameter("sessionId");

        List<Book> books = libraryService.getAvailableBooks();

        response.writeBody("<h2>대출 가능한 도서 목록</h2>");
        response.writeBody("<ul>");

        for (Book b : books) {
            response.writeBody("<li>");
            response.writeBody(b.getTitle() + " - " + b.getAuthor());
            response.writeBody("</li>");
        }

        response.writeBody("</ul>");
        response.writeBody("<a href='/user?sessionId=" + sid + "'>뒤로가기</a>");
    }
}
