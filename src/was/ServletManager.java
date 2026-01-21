package was;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import web.controller.NotFoundServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServletManager {
    private final Map<String, HttpServlet> servletMap = new HashMap<>();
    private final HttpServlet notFoundServlet = new NotFoundServlet();

    public void add(String path, HttpServlet servlet) {
        servletMap.put(path, servlet);
    }

    public void execute(HttpRequest request, HttpResponse response) throws IOException {
        HttpServlet servlet = servletMap.getOrDefault(request.getPath(), notFoundServlet);
        servlet.service(request, response);
    }
}
