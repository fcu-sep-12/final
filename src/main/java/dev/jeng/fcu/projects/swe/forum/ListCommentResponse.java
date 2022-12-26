package dev.jeng.fcu.projects.swe.forum;

import java.util.List;

public class ListCommentResponse {
    public Boolean success;
    public List<Comment> comments;

    public ListCommentResponse(Boolean status, List<Comment> comments) {
        this.success = status;
        this.comments = comments;
    }
}
