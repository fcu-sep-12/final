package dev.jeng.fcu.projects.swe.forum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class States {
    public static HashMap<String, User> Users = new HashMap<String, User>();
    public static HashMap<String, String> Sessions = new HashMap<String, String>();
    public static List<Board> Boards = new ArrayList<>();
    public static List<Comment> Comments = new ArrayList<>();
    static {
        States.Users.put("test", new User("test", "test123"));
		States.Users.put("Heidi", new User("Heidi", "test123"));
		States.Users.get("test").login("test123");
		States.Boards.add(new Board("test"));
		States.Comments.add(new Comment("test", "hi"));
		States.Comments.add(new Comment("test2", "hi2"));
    }
}
