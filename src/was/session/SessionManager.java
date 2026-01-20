package was.session;

import domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
    private final Map<String, Session> sessions = new HashMap<>();

    public String createSession(User user) {
        String id = UUID.randomUUID().toString();

        Session session = new Session(user);
        sessions.put(id, session);

        return id;
    }

    public User getUser(String sessionId) {
        Session s = sessions.get(sessionId);
        if (s == null) return null;
        return s.getCurrentUser();
    }
}
