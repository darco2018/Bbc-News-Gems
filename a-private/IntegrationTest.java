package cloud.javacoder.bbcnewsgems.integration;

import cloud.javacoder.bbcnewsgems.headlines.HeadlinesDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenHeadlinesEndpoint_returnsJSONAndStatusOK() throws Exception{

        ResponseEntity<HeadlinesDTO> response = this.restTemplate.getForEntity("/headlines", HeadlinesDTO.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //Assertions.assertThat(response.getHeaders().getContentType()).isEqualTo("application/json");
    }

    @Test
    public void whenHeadlinesEndpoint_returnsValidJSONProperties() throws Exception{

        ResponseEntity<HeadlinesDTO> response = this.restTemplate.getForEntity("/headlines", HeadlinesDTO.class);

        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getVersion()).isNotEmpty();
       // Assertions.assertThat(response.getBody().getDescription()).isNotEmpty();
        Assertions.assertThat(response.getBody()).hasFieldOrProperty("size");
        Assertions.assertThat(response.getBody()).hasFieldOrProperty("data");
        /*Assertions.assertThat(response.getBody().getData()).hasSizeGreaterThan(0);*/

    }
}
