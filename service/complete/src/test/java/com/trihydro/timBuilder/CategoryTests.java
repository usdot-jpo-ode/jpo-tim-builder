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
public class CategoryTests {
	
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
    public void testCategoriesGetAll() throws Exception {
		// test selecting all Categories 
		mockMvc.perform(get("/categories"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(4)))
			.andExpect(jsonPath("$[0].categoryId", is(1)))
			.andExpect(jsonPath("$[0].category", is("speedLimit")))
			.andExpect(jsonPath("$[1].categoryId", is(2)))
			.andExpect(jsonPath("$[1].category", is("advisory")))
			.andExpect(jsonPath("$[2].categoryId", is(3)))
			.andExpect(jsonPath("$[2].category", is("workZone")))
			.andExpect(jsonPath("$[3].categoryId", is(4)))
			.andExpect(jsonPath("$[3].category", is("exitService")))
			.andDo(print());		
    }
}