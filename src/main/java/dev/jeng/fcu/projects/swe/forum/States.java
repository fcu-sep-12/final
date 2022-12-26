package dev.jeng.fcu.projects.swe.forum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class States {
    public static HashMap<String, User> Users = new HashMap<String, User>();
    public static HashMap<String, String> Sessions = new HashMap<String, String>();
    public static List<Board> Boards = new ArrayList<>();
    public static List<Comment> Comments = new ArrayList<>();
}
