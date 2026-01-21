package web.controller;

import Library.LibraryService;
import domain.User;
import util.FlashMessage;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.session.SessionManager;
import web.command.CommandManager;
import web.command.CommandResult;
import web.command.action.AddBookCommand;
import web.command.action.AddUserCommand;
import web.content.AddBookContent;
import web.content.AddUserContent;
import web.content.NavContent;
import web.template.BaseTemplate;

import java.io.IOException;

public class AdminServlet implements HttpServlet {
    private final SessionManager sessionManager;
    private final CommandManager commands = new CommandManager();

    public AdminServlet(LibraryService libraryService, SessionManager sessionManager) {
        this.sessionManager = sessionManager;

        commands.register("addBook",
                new AddBookCommand(libraryService, sessionManager));
        commands.register("addUser"
                , new AddUserCommand(libraryService, sessionManager));
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String sid = request.getParameter("sessionId");
        User currentUser = (sid == null) ? null : sessionManager.getUser(sid);
        if (currentUser == null) {
            response.sendDirect("/");
            return;
        }

        String action = request.getParameter("action");
        if (action != null) {
            CommandResult actionResult = commands.execute(action, request);

            if (actionResult.redirect) {
                response.sendDirect(actionResult.redirectUrl);
                return;
            }
        }

        String flash = request.getParameter("flash");
        String flashMsg = FlashMessage.admin(flash);

        StringBuilder page = new StringBuilder();
        if (flashMsg != null) {
            page.append(FlashMessage.wrapFlashBox(flashMsg));
        }

        String nav = NavContent.render(sid);

        page.append(AddBookContent.renderAddBook(sid));
        page.append("<hr>");

        page.append(AddUserContent.renderAddUser(sid));
        page.append("<hr>");

        page.append("<a class='btn' href='/'>HOME</a>");

        response.writeBody(BaseTemplate.render("Admin", nav, page.toString()));
    }
}
