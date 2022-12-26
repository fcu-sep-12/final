package dev.jeng.fcu.projects.swe.forum;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumController {
    @CrossOrigin
    @PostMapping("/api/login")
    public HashMap<String, Object> login(@RequestBody HashMap<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        User user = States.Users.get(username);
        String session = user.login(password);
        if (session == null) {
            HashMap<String, Object> result = new HashMap<String, Object>();
            result.put("success", false);
            result.put("message", "Login failed.");
            return result;
        }
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("token", session);
        return result;
    }

    @CrossOrigin
    @GetMapping("/api/boards/list")
    public HashMap<String, Object> listBoards() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("boards", States.Boards);
        return result;
    }

    @CrossOrigin
    @GetMapping("/api/comments")
    public List<Comment> listComments() {
        return States.Comments;
    }

    @CrossOrigin
    @PostMapping("/api/comments")
    public Comment createComment(@RequestBody HashMap<String, String> request) {
        String username = request.get("nickname");
        String body = request.get("body");
        if (username == null || body == null) {
            return new Comment(null, null);
        }
        Comment newComment = new Comment(username, body);
        States.Comments.add(newComment);
        return newComment;
    }

    @CrossOrigin
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        States.Comments.remove(id.intValue());
        return new ResponseEntity<String>("{}", HttpStatus.OK);
    }
}
