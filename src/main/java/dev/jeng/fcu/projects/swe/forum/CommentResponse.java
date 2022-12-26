package dev.jeng.fcu.projects.swe.forum;

public class CommentResponse {
    public Boolean success;
    public Integer id;

    public CommentResponse(Boolean status, Integer id) {
        this.success = status;
        this.id = id;
    }
}
