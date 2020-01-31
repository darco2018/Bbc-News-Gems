package cloud.javacoder.bbcnewsgems.integration;

import cloud.javacoder.bbcnewsgems.headlines.HeadlinesDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeadlinesControllerIntegrationTest {

    private static String HEADLINES_URL = "/headlines";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getHeadlines_shouldReturnHeadlines() {
        ResponseEntity<HeadlinesDTO> response = restTemplate.getForEntity(HEADLINES_URL, HeadlinesDTO.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getVersion()).isEqualTo("1.0.0");
        Assertions.assertThat(response.getBody().getData()).size().isGreaterThan(0);
    }


}
