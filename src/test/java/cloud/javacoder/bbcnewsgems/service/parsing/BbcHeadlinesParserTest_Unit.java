package cloud.javacoder.bbcnewsgems.service.parsing;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BbcHeadlinesParserTest_Unit {

    private static final String BBC_HOMEPAGE_FILE = "bbc.html";
    private static String BBC_HTML;

    private final HTMLParser htmlParser = new BbcHeadlinesParser();

    @BeforeAll
    private static void getBbcHtmlFromFile() throws Exception {
        byte[] htmlBytes = Files.readAllBytes(Paths.get(BBC_HOMEPAGE_FILE));
        BBC_HTML = new String(htmlBytes);
    }

    @DisplayName("Parses BBC homepage into headlines")
    @Test
    void givenBbcHtmlAsString_parsesHeadlines() {

        List<String> headlines = this.htmlParser.parse(BBC_HTML);

        Assertions.assertThat(headlines)
                .isNotNull()
                .doesNotContainNull()
                .isInstanceOfAny(List.class)
                .hasSize(67)
                .asString().contains("Coronavirus: Airline asks staff");
    }

    @Test
    void givenUnparsableContent_returnsEmptyList() {

        List<String> headlines = this.htmlParser.parse("You can't parse it into headlines!");

        Assertions.assertThat(headlines)
                .isNotNull()
                .isInstanceOfAny(List.class)
                .hasSize(0);
    }
}
