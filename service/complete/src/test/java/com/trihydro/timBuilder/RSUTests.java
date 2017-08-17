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
public class RSUTests {
	
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
    public void testRSUs() throws Exception {
		//test the group get 
		mockMvc.perform(get("/rsus"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].rsuId", is(1)))
			.andExpect(jsonPath("$[0].rsuTarget", is("192.168.0.145")))
			.andExpect(jsonPath("$[0].rsuUsername", is("v3user")))
			.andExpect(jsonPath("$[0].rsuPassword", is("password")))
			.andExpect(jsonPath("$[0].latitude", is(41.123456)))
			.andExpect(jsonPath("$[0].longitude", is(110.123456)))
			.andExpect(jsonPath("$[1].rsuId", is(2)))
			.andExpect(jsonPath("$[1].rsuTarget", is("10.145.10.15")))
			.andExpect(jsonPath("$[1].rsuUsername", is("v3user")))
			.andExpect(jsonPath("$[1].rsuPassword", is("password")))
			.andExpect(jsonPath("$[1].latitude", is(41.87654)))
			.andExpect(jsonPath("$[1].longitude", is(110.87654)))
			.andDo(print());		
    }
}