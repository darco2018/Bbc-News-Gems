package cloud.javacoder.bbcnewsgems.service.fetching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;

public class FetchingServiceUnitTest {

    private final String BBC = "https://www.bbc.com/";
    private FetchingService fetchingService = new FetchingServiceImpl();

    @DisplayName("Fetches BBC homepage")
    @Test
    public void givenBbcUrl_fetchesHtml() throws Exception {

        String html = fetchingService.getHtml(new URL(BBC).toURI());

        Assertions.assertThat(html)
                .isNotNull()
                .isNotBlank()
                .isNotEmpty()
                .hasSizeGreaterThan(500)
                .containsIgnoringCase("<!DOCTYPE html>")
                .containsIgnoringCase("bbc")
                .contains("<title>", "<body>");

    }

    // how to do when resttemplate throws exception ?!
    @DisplayName("when fetching from BBC unsuccessful returns empty string ")
    @Test
    public void givenWrongUrl_returnsEmptyString() throws Exception {
        String html = fetchingService.getHtml(new URL("http://nonexistenturlXYZ.com").toURI());

        Assertions.assertThat(html)
                .isBlank();
    }
}

