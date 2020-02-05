package cloud.javacoder.bbcnewsgems.service.fetching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FetchingServiceUnitTest {

    private static String BBC_URL = "https://www.bbc.com/";
    private FetchingService fetchingService = new FetchingServiceImpl();

    @DisplayName("Fetches BBC homepage")
    @Test
    public void givenBbcUrl_fetchesHtml() {

        String html = fetchingService.getHtml(BBC_URL);

        Assertions.assertThat(html)
                .isNotNull()
                .isNotBlank()
                .isNotEmpty()
                .hasSizeGreaterThan(500)
                .containsIgnoringCase("<title>")
                .containsIgnoringCase("<body>")
                .containsIgnoringCase("bbc");
    }

    // how to do when resttemplate throws exception ?!
    @DisplayName("when fetching from BBC unsuccessful returns empty string ")
    @Test
    public void givenWrongUrl_returnsEmptyString(){
        String html = fetchingService.getHtml("http://nonexistenturl.com");

        Assertions.assertThat(html)
                .isBlank();
    }
}

