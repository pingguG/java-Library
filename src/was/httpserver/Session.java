package was.httpserver;

import model.*;

public class Session {

    private final User currentUser;

    public Session(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
