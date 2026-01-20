package was.httpserver;

import Library.LibraryService;
import was.version.servlet.*;

import java.io.*;
import java.util.*;

public class ServletManager {
    private final Map<String, HttpServlet> servletMap = new HashMap<>();
    private final HttpServlet notFoundServlet = new NotFoundServlet();
    private final LibraryService libraryService;
    private final SessionManager sessionManager;

    public ServletManager(LibraryService libraryService, SessionManager sessionManager) {
        this.libraryService = libraryService;
        this.sessionManager = sessionManager;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void add(String path, HttpServlet servlet) {
        servletMap.put(path, servlet);
    }

    public void execute(HttpRequest request, HttpResponse response) throws IOException {
        HttpServlet servlet = servletMap.getOrDefault(request.getPath(), notFoundServlet);
        servlet.service(request, response);
    }
}
