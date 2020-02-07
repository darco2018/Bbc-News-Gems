package cloud.javacoder.bbcnewsgems.service.fetching;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
public class FetchingServiceImpl implements FetchingService {

    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    @Override
    public String getHtml(URI url) {
        log.info("Starting fetching from " + url);

        try {
            String html = restTemplate.getForObject(url, String.class);
            log.info("Fetched " + html.length() + " characters from " + url);

            return html;

        } catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

}
