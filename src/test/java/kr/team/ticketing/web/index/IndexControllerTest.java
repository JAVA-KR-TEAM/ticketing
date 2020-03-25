package kr.team.ticketing.web.index;

import kr.team.ticketing.common.ControllerTests;

import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IndexControllerTest extends ControllerTests {
	@Test
	void index() throws Exception {
		this.mockMvc.perform(get("/api"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().string("Hello Ticket"))
			.andDo(document("index"));
	}
}