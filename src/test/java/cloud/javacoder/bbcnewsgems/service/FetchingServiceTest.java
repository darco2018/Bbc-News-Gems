package cloud.javacoder.bbcnewsgems.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class FetchingServiceTest {

    private static String BBC_URL = "https://www.bbc.com/";

    // you cannot expect that Spring will @Autowire  anything, if you don't use @SpringBootTest or other @Spring anno

    @Mock
    private HTMLParser htmlParser;

    @InjectMocks // In Mockito you must instantiate for an interface manually
    private FetchingService fetchingService = new FetchingServiceImpl(htmlParser);
   // otherwise exception:
    // Cannot instantiate @InjectMocks field named 'fetchingService'! Cause: the type 'FetchingService' is an interface.
    //You haven't provided the instance at field declaration so I tried to construct the instance.

    //given(htmlParser.parseHeadlines(anyString())).willReturn(anyList());

/*
    @DisplayName("Test Spring @Autowired integration")
    @Test
    public void allComponentsAreWiredUp() {
        String html = fetchingService.getHtml(anyString()); // stops working after adding RestTemplate (wrong, not absolute URL)
        Assertions.assertThat(html).isNullOrEmpty();
    }
*/

    // The next test produces only 4 lines of output from DEBUG org.springframework.web.client.RestTemplate
    @DisplayName("Fetches from BBC")
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
