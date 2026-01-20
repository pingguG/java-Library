package web.command;

import was.httpserver.HttpRequest;

public interface Command {
    CommandResult execute(HttpRequest request);
}
