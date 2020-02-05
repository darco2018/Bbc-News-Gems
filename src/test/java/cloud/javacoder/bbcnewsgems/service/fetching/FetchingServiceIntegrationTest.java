package cloud.javacoder.bbcnewsgems.service.fetching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// starts Spring context: you can see Spring logo
@SpringBootTest(classes = {FetchingServiceImpl.class})
public class FetchingServiceIntegrationTest {

    private static String BBC_URL = "https://www.bbc.com/";

    @Autowired // @Autowired works only with @SpringBootTest;
    // limit classes involved by (classes = {FetchingServiceImpl.class}) turns off db, jpa, web, ...
    private FetchingService fetchingService;

    @DisplayName("Fetches BBC homepage")
    @Test
    public void givenBbcUrl_returnsBbcWebpage() {
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
}
