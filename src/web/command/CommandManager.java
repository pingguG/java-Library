package web.command;

import was.httpserver.HttpRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String action, Command command) {
        commands.put(action, command);
    }

    public CommandResult execute(String action, HttpRequest request) {
        Command cmd = commands.get(action);
        if (cmd == null) {
            return CommandResult.redirect("/");
        }
        return cmd.execute(request);
    }
}

