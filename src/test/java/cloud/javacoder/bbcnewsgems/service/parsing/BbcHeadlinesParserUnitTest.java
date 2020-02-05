package cloud.javacoder.bbcnewsgems.service.parsing;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BbcHeadlinesParserUnitTest {

    private final HTMLParser htmlParser = new BbcHeadlinesParser();
    private static final String BBC_HOMEPAGE_FILE = "bbc.html";
    private static String bbcHomepageStr;

    @BeforeAll //  MUST be a static method
    private static void loadBbcHtmlFromFile() throws IOException {
        byte[] htmlBytes = Files.readAllBytes(Paths.get(BBC_HOMEPAGE_FILE));
        bbcHomepageStr = new String(htmlBytes);
    }
    @DisplayName("Parses BBC homepage into headlines")
    @Test
    void givenBbcHtmlAsString_parsesHeadlines() {

        List<String> headlines = this.htmlParser.parse(bbcHomepageStr);

        Assertions.assertThat(headlines)
                .isNotNull()
                .doesNotContainNull()
                .hasSize(67)
                .asString().contains("Coronavirus: Airline asks staff");
    }

    @DisplayName("when cannot parse, returns empty list")
    @Test
    void givenUnparsableContent_returnsEmptyList() {

        List<String> headlines = this.htmlParser.parse("You can't parse it into headlines!");

        Assertions.assertThat(headlines)
                .isNotNull()
                .hasSize(0);
    }
}
