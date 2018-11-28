package hello;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Now we use a Controller that has a dependency onto another service
 * (the GreetingService).
 * If we had used @SpringBootTest no problem Spring would have wired everything.
 * 
 * BUT it would have been long and heavy...
 * 
 * => so we just use @WebMvcTest(GreetingController.class) to intantiate only the
 * Controller as a Spring bean and mock the GreetingService.
 * This is especially interesting in a real application where the GreetingService
 * itself has dependency who have dependencies and so on...
 * 
 * Because here our goal is to test specifically GreetingController, we can
 * mock it's dependencies. 
 * 
 * 
 * We use @MockBean to create and inject a mock for the 
 * GreetingService (if you don’t do this the application 
 * context cannot start), and we set its expectations 
 * using Mockito.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class _4_WebMockTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GreetingService service;

	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		
		// configure the mock
		when(service.greet()).thenReturn("Hello Mock");
		
		// do the test
		this.mockMvc.perform(get("/greeting"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello Mock")));
	}
}
