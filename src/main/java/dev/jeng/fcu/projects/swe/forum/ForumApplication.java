package dev.jeng.fcu.projects.swe.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {
		States.Users.put("test", new User("test", "test123"));
		States.Users.put("Heidi", new User("Heidi", "test123"));
		States.Users.get("test").login("test123");
		States.Boards.add(new Board("test"));
		States.Comments.add(new Comment("test", "hi"));
		States.Comments.add(new Comment("test2", "hi2"));
		SpringApplication.run(ForumApplication.class, args);
	}

}
