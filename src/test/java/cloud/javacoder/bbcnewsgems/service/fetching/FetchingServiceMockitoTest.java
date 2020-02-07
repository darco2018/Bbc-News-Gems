package cloud.javacoder.bbcnewsgems.service.fetching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;

// bez sensu. ten sam output co Unit test. nic nie jest mockowane tak na prawde. test wchodzi w implementacje metody
// .getHtml()

@ExtendWith(MockitoExtension.class)
public class FetchingServiceMockitoTest {

    private final String BBC = "https://www.bbc.com/";

    @InjectMocks
    private FetchingService fetchingService = new FetchingServiceImpl();

    // The next test produces only 4 lines of output from DEBUG org.springframework.web.client.RestTemplate
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
}


/*
* @InjectMocks // In Mockito you must instantiate for an interface manually
    private FetchingService fetchingService = new FetchingServiceImpl();
   // otherwise exception:
    // Cannot instantiate @InjectMocks field named 'fetchingService'! Cause: the type 'FetchingService' is an interface.
    //You haven't provided the instance at field declaration so I tried to construct the instance.
*
* */

// you cannot expect that Spring will @Autowire  anything, if you don't use @SpringBootTest or other @Spring anno
