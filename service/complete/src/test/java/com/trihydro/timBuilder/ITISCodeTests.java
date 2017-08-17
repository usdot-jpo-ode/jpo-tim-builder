package com.trihydro.timBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ITISCodeTests {
	
	@Autowired
	private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(this.webContext)
			.build();
	}

    @Test
    public void testITISCodes() throws Exception {
		// test selecting all ITISCodes
		mockMvc.perform(get("/itiscodes"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].itisCodeId", is(1)))
			.andExpect(jsonPath("$[0].itisCode", is(268)))
			.andExpect(jsonPath("$[0].description", is("Speed Limit")))
			.andExpect(jsonPath("$[0].categoryId", is(1)))
			.andExpect(jsonPath("$[1].itisCodeId", is(2)))
			.andExpect(jsonPath("$[1].itisCode", is(513)))
			.andExpect(jsonPath("$[1].description", is("Accident")))
			.andExpect(jsonPath("$[1].categoryId", is(2)))
			.andDo(print());					
    }
}