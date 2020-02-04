package cloud.javacoder.bbcnewsgems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FetchingServiceImpl implements FetchingService {

    private HTMLParser htmlParser;

    // implicit constructor injection
    public FetchingServiceImpl(HTMLParser htmlParser) {
        this.htmlParser = htmlParser;
    }


    @Override
    public String getHtml(String url) {
       /*
         if(url is not a valid URI){
            do stuff
        }
        */
        RestTemplate restTemplate = new RestTemplate();
        String html = restTemplate.getForObject(url, String.class);
        return html;
    }
}

// RestTemplate. getForObject String.class
// makes a call to bbc
// gets html
// uses parsing library
// returns List of string headlines

