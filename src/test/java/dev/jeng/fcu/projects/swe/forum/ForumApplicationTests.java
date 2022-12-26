package dev.jeng.fcu.projects.swe.forum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ForumApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void apiBoardsList() throws Exception {
		mockMvc.perform(get("/api/boards/list"))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"success\":true,\"boards\":[{\"id\":0,\"name\":\"test\"}]}")); // pass
	}

	@Test
	void apiCommentsGet() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/comments"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = result.getResponse().getContentAsString();
		TypeToken<List<Comment>> mapType = new TypeToken<List<Comment>>() {};
		List<Comment> data = new Gson().fromJson(content, mapType);
		assertEquals(data.get(0).nickname, "test");
		assertEquals(data.get(0).body, "hi");
		assertEquals(data.get(0).id, 0);
		assertEquals(data.get(1).nickname, "test2");
		assertEquals(data.get(1).body, "hi2");
		assertEquals(data.get(1).id, 1);
	}

	@Test
	void apiCommentsPost() throws Exception {
		final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
		MvcResult result = mockMvc.perform(post("/api/comments").contentType(APPLICATION_JSON_UTF8)
        .content("{\"nickname\":\"H\",\"body\":\"aaaa\"}"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = result.getResponse().getContentAsString();
		Comment data = new Gson().fromJson(content, Comment.class);
		assertEquals(data.nickname, "H");
		assertEquals(data.body, "aaaa");
		MvcResult result2 = mockMvc.perform(get("/api/comments"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content2 = result2.getResponse().getContentAsString();
		TypeToken<List<Comment>> mapType = new TypeToken<List<Comment>>() {};
		List<Comment> data2 = new Gson().fromJson(content2, mapType);
		assertEquals(data2.get(2).nickname, "H");
		assertEquals(data2.get(2).body, "aaaa");
		assertEquals(data2.get(2).id, 2);
	}

	@Test
	void apiCommentsDelete() throws Exception {
		mockMvc.perform(delete("/api/comments/0"))
				.andExpect(status().isOk())
				.andExpect(content().string("{}"));
				MvcResult result = mockMvc.perform(get("/api/comments"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = result.getResponse().getContentAsString();
		TypeToken<List<Comment>> mapType = new TypeToken<List<Comment>>() {};
		List<Comment> data = new Gson().fromJson(content, mapType);
		assertEquals(data.get(0).nickname, "test2");
		assertEquals(data.get(0).body, "hi2");
		assertEquals(data.get(0).id, 1);
	}

	@Test
	void apiLoginSuccess() throws Exception {
		final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
		MvcResult result = mockMvc.perform(post("/api/login").contentType(APPLICATION_JSON_UTF8)
        .content("{\"username\":\"test\",\"password\":\"test123\"}"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = result.getResponse().getContentAsString();
		TypeToken<HashMap<String, Object>> mapType = new TypeToken<HashMap<String, Object>>() {};
		HashMap<String, Object> data = new Gson().fromJson(content, mapType);
		assertEquals(data.get("success"), true);
	}

	@Test
	void apiLoginFail() throws Exception {
		final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
		MvcResult result = mockMvc.perform(post("/api/login").contentType(APPLICATION_JSON_UTF8)
        .content("{\"username\":\"test\",\"password\":\"incorrect-password\"}"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = result.getResponse().getContentAsString();
		TypeToken<HashMap<String, Object>> mapType = new TypeToken<HashMap<String, Object>>() {};
		HashMap<String, Object> data = new Gson().fromJson(content, mapType);
		assertEquals(data.get("success"), false);
	}
}
