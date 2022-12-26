package dev.jeng.fcu.projects.swe.forum;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final String username;
    private String password;
    private String session;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String login(String password) {
        if (Objects.equals(password, this.password)) {
            this.session = UUID.randomUUID().toString();
            States.Sessions.put(this.session, this.username);
            return this.session;
        }
        return null;
    }

    public Boolean logout(String session) {
        if (checkSession(session)) {
            this.session = null;
            return true;
        }
        return false;
    }

    public String getUsernameString() {
        return username;
    }

    public Boolean checkSession(String session) {
        if (Objects.equals(session, this.session) && this.session != null) {
            return true;
        }
        return false;
    }
}
