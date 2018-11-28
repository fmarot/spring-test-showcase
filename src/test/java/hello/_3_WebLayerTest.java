package hello;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * The test assertion is the same as in the previous case (ApplicationTest
 * & HttpRequestTest), but here Spring Boot is only instantiating the web
 * layer, not the whole context. In an application with multiple controllers
 * you can even ask for just one to be instantiated, using,
 * for example @WebMvcTest(HomeController.class)
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)	// here we ask just one controller to be instantiated (we could set empty to instantiate ALL web controllers)
public class _3_WebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello World")));
	}
}
