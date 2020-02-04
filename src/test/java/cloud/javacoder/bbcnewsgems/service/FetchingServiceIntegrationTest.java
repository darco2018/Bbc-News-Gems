package cloud.javacoder.bbcnewsgems.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// IMPORTANT: You must specify the implementation classes of the interface you use below:
@SpringBootTest(classes = {FetchingServiceImpl.class, HTMLParserImpl.class})
public class FetchingServiceIntegrationTest {

    private static String BBC_URL = "https://www.bbc.com/";

    @Autowired // @Autowired works only with @SpringBootTest
    private FetchingService fetchingService;

    @Test
    public void getsBBCHtml(){
        String htmlBody = fetchingService.getHtml(BBC_URL);

        Assertions.assertThat(htmlBody).isNullOrEmpty();
    }

}
