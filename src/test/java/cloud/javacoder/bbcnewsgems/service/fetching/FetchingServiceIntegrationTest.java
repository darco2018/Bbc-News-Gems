package cloud.javacoder.bbcnewsgems.service.fetching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URL;

// starts Spring context: you can see Spring logo
@SpringBootTest(classes = {FetchingServiceImpl.class})
public class FetchingServiceIntegrationTest {

    private final String BBC = "https://www.bbc.com/";

    @Autowired // @Autowired works only with @SpringBootTest;
    // limit classes involved by (classes = {FetchingServiceImpl.class}) turns off db, jpa, web, ...
    private FetchingService fetchingService;

    @DisplayName("Fetches BBC homepage")
    @Test
    public void givenBbcUrl_returnsBbcWebpage() throws Exception {
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
}
