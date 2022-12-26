package dev.jeng.fcu.projects.swe.forum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	void apiComments() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/comments"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = result.getResponse().getContentAsString();
		TypeToken<List<Comment>> mapType = new TypeToken<List<Comment>>(){};
		List<Comment> data = new Gson().fromJson(content, mapType);
		assertEquals(data.get(0).nickname, "test");
		assertEquals(data.get(0).body, "hi");
		assertEquals(data.get(0).id, 0);
		assertEquals(data.get(1).nickname, "test2");
		assertEquals(data.get(1).body, "hi2");
		assertEquals(data.get(1).id, 1);
	}
}
