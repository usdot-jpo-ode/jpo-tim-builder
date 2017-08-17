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
public class MilepostTests {
	
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
    public void testMilepostsGetAll() throws Exception {
		//test getting all mileposts
		mockMvc.perform(get("/mileposts"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(3)))
			.andExpect(jsonPath("$[0].milepostId", is(1)))
			.andExpect(jsonPath("$[0].route", is("I 80")))
			.andExpect(jsonPath("$[0].milepost", is(0.0)))
			.andExpect(jsonPath("$[0].direction", is("eastbound")))
			.andExpect(jsonPath("$[0].latitude", is(41.24763487)))
			.andExpect(jsonPath("$[0].longitude", is(-111.04672368)))
			.andExpect(jsonPath("$[0].elevation", is(6758.03058453)))
			.andExpect(jsonPath("$[0].bearing", is(111.13333269)))
			.andExpect(jsonPath("$[1].milepostId", is(2)))
			.andExpect(jsonPath("$[1].route", is("I 80")))
			.andExpect(jsonPath("$[1].milepost", is(1.0)))
			.andExpect(jsonPath("$[1].direction", is("eastbound")))
			.andExpect(jsonPath("$[1].latitude", is(41.24816495)))
			.andExpect(jsonPath("$[1].longitude", is(-111.04672312)))
			.andExpect(jsonPath("$[1].elevation", is(6751.60266925)))
			.andExpect(jsonPath("$[1].bearing", is(0.0)))
			.andExpect(jsonPath("$[2].milepostId", is(3)))
			.andExpect(jsonPath("$[2].route", is("I 80")))
			.andExpect(jsonPath("$[2].milepost", is(2.0)))
			.andExpect(jsonPath("$[2].direction", is("eastbound")))
			.andExpect(jsonPath("$[2].latitude", is(41.24638234)))
			.andExpect(jsonPath("$[2].longitude", is(-111.027816)))
			.andExpect(jsonPath("$[2].elevation", is(6753.52824038)))
			.andExpect(jsonPath("$[2].bearing", is(89.08132948)))
			.andDo(print());				
    }

    @Test
    public void testMilepostsGetRange() throws Exception {
		// test selecting mileposts by range and direction
		mockMvc.perform(get("/getMilepostRange/eastbound/0/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].milepostId", is(1)))
			.andExpect(jsonPath("$[0].route", is("I 80")))
			.andExpect(jsonPath("$[0].milepost", is(0.0)))
			.andExpect(jsonPath("$[0].direction", is("eastbound")))
			.andExpect(jsonPath("$[0].latitude", is(41.24763487)))
			.andExpect(jsonPath("$[0].longitude", is(-111.04672368)))
			.andExpect(jsonPath("$[0].elevation", is(6758.03058453)))
			.andExpect(jsonPath("$[0].bearing", is(111.13333269)))
			.andExpect(jsonPath("$[1].milepostId", is(2)))
			.andExpect(jsonPath("$[1].route", is("I 80")))
			.andExpect(jsonPath("$[1].milepost", is(1.0)))
			.andExpect(jsonPath("$[1].direction", is("eastbound")))
			.andExpect(jsonPath("$[1].latitude", is(41.24816495)))
			.andExpect(jsonPath("$[1].longitude", is(-111.04672312)))
			.andExpect(jsonPath("$[1].elevation", is(6751.60266925)))
			.andExpect(jsonPath("$[1].bearing", is(0.0)))
			.andDo(print());
    }
}