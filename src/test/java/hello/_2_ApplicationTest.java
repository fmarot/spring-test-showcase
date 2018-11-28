package hello;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * This test simulates the real application but without a real HTTP layer (=> light)
 * 
 * From the docs: 
 * "Another useful approach is to not start the server at all, but test 
 * only the layer below that, where Spring handles the incoming HTTP 
 * request and hands it off to your controller. That way, almost the 
 * full stack is used, and your code will be called exactly the 
 * same way as if it was processing a real HTTP request, but without 
 * the cost of starting the server. To do that we will use Spring’s 
 * MockMvc, and we can ask for that to be injected for us by using 
 * the @AutoConfigureMockMvc annotation on the test case"
 * 
 * 
 * In this test, the full Spring application context is started, but without the server
 */
@RunWith(SpringRunner.class)
@SpringBootTest /*(default WebEnvironment.MOCK)*/ // tells Spring Boot to go and look for a main configuration class (one with
				// @SpringBootApplication for instance
@AutoConfigureMockMvc // enable and configure auto-configuration of MockMvc
public class _2_ApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello World from HomeController")));
	}
}
