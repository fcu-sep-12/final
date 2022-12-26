package dev.jeng.fcu.projects.swe.forum;

public class Comment {
    public String nickname;
    public String body;
    public Long createdAt;
    public Integer id;

    public Comment(String username, String body) {
        this.nickname = username;
        this.body = body;
        this.createdAt = System.currentTimeMillis();
        this.id = States.Comments.size();
    }
}
