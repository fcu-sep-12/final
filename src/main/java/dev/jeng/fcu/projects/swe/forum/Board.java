package dev.jeng.fcu.projects.swe.forum;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public Integer id;
    public String name;
    private List<Comment> comments;

    public Board(String name) {
        this.name = name;
        this.comments = new ArrayList<>();
        this.id = States.Boards.size();
    }

    public List<Comment> listComment() {
        return this.comments;
    }
}
