package was;

import Library.LibraryMap;
import Library.LibraryService;
import Library.LibraryStorage;
import was.httpserver.HttpServer;
import was.session.SessionManager;
import web.controller.AdminServlet;
import web.controller.ExitServlet;
import web.controller.HomeServlet;
import web.controller.UserServlet;

import java.io.IOException;

public class ServerMainV4 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        LibraryMap libraryMap = new LibraryMap();
        LibraryStorage storage = new LibraryStorage();
        storage.loadInto(libraryMap);

        LibraryService libraryService = new LibraryService(libraryMap);
        SessionManager sessionManager = new SessionManager();
        ServletManager servletManager = new ServletManager();

        // 필요한 것만 주입
        servletManager.add("/", new HomeServlet(libraryService, sessionManager));
        servletManager.add("/admin", new AdminServlet(libraryService, sessionManager));
        servletManager.add("/user", new UserServlet(libraryService, sessionManager));

        servletManager.add("/exit", new ExitServlet());

        HttpServer server = new HttpServer(PORT, servletManager, libraryService,storage);
        server.start();
    }
}