package hello;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;

/**
 * This test simulates the real application but with a real HTTP layer (=> not light and slow ! :( )
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)	// real value decided at runtime will be injected in field annotated with @LocalServerPort
public class _1_HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String url;

	@PostConstruct
	void constructURL() {
		url = "http://localhost:" + port + "/";
	}

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).contains("Hello World from HomeController");
	}

	@Test
	public void greetingShouldHaveCorrectStatus() throws Exception {
		ResponseEntity<String> responseEntity = restTemplate.exchange(
				url, HttpMethod.GET,
				new HttpEntity<Void>(new HttpHeaders()),
				String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody())
				.contains("Hello World from HomeController");
	}
}
