package was.version;

import was.httpserver.*;
import Library.*;
import was.version.servlet.*;
import was.version.servlet.admin.*;
import was.version.servlet.user.*;

import java.io.IOException;

public class ServerMainV4 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        LibraryMap libraryMap = new LibraryMap();
        LibraryStorage storage = new LibraryStorage();
        storage.loadInto(libraryMap);

        LibraryService libraryService = new LibraryService(libraryMap);
        SessionManager sessionManager = new SessionManager();

        ServletManager servletManager = new ServletManager(libraryService, sessionManager);

        servletManager.add("/", new HomeServlet());
        servletManager.add("/login", new LoginServlet());
        servletManager.add("/admin", new AdminServlet());
        servletManager.add("/user", new UserServlet());

        servletManager.add("/addBook", new AddBookServlet());
        servletManager.add("/addUser", new AddUserServlet());

        servletManager.add("/bookList", new BookListServlet());
        servletManager.add("/borrowBook", new BorrowServlet());
        servletManager.add("/returnBook", new ReturnBookServlet());
        servletManager.add("/Overdue", new OverdueServlet());

        servletManager.add("/exit", new ExitServlet());

        HttpServer server = new HttpServer(PORT, servletManager, storage);
        server.start();
    }
}