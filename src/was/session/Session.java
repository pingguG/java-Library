package was.session;

import domain.User;

public class Session {

    private final User currentUser;

    public Session(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
