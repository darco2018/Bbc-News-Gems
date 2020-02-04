package cloud.javacoder.bbcnewsgems.service;

import org.springframework.stereotype.Service;

@Service
public class FetchingServiceImpl implements FetchingService {

    private HTMLParser htmlParser;
    // implicit constructor injection
    public FetchingServiceImpl(HTMLParser htmlParser) {
        this.htmlParser = htmlParser;
    }

    //this.htmlParser.parseHeadlines("");

    @Override
    public String getHtml(String url) {

        return null;
    }
}

// RestTemplate. getForObject String.class
// makes a call to bbc
// gets html
// uses parsing library
// returns List of string headlines

