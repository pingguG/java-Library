package domain;

public abstract class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // null이거나 user가 아니면 바로 터지는 상황 방지
        if (this == obj) return true;
        if (!(obj instanceof User other)) return false;
        return username.equals(other.username);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}


