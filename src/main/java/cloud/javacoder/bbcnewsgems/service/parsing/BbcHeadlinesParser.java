package cloud.javacoder.bbcnewsgems.service.parsing;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BbcHeadlinesParser implements HTMLParser {

    private final String HEADLINES_SELECTOR = "data-bbc-title";

    @Override
    public List<String> parse(final String html) {

        log.info("Starting tha parsing of html of " +  html.length() + " characters with the selector: " + HEADLINES_SELECTOR);

        List<String> headlines = new ArrayList<>();

        Document doc = Jsoup.parse(html);
        Elements headlineElems = doc.getElementsByAttribute(HEADLINES_SELECTOR);

        log.info("Parsed html into " + headlineElems.size() + " elements");

        for (Element elem : headlineElems){
            String headline = elem.attr(HEADLINES_SELECTOR);
            headlines.add(headline);
            log.trace(headline);
        }

        return headlines;
    }
}
