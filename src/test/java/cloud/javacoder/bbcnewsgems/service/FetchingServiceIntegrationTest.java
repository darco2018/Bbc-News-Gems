package cloud.javacoder.bbcnewsgems.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

// IMPORTANT: You must specify the implementation classes of the interface you use below:
@SpringBootTest(classes = {FetchingServiceImpl.class, HTMLParserImpl.class})
public class FetchingServiceIntegrationTest {

    private static String BBC_URL = "https://www.bbc.com/";

    @Autowired // @Autowired works only with @SpringBootTest
    private FetchingService fetchingService;

    // stqarts Spring context: you cane see Spring logo
    @Test
    public void givenBbcUrl_fetchesHtmlFromBbcHomepage(){
        String html = fetchingService.getHtml(BBC_URL);

        Assertions.assertThat(html)
                .isNotBlank()
                .isNotEmpty()
                .containsIgnoringCase("<title>")
                .containsIgnoringCase("<body>")
                .containsIgnoringCase("bbc")
                .hasSizeGreaterThan(500);
    }

}
