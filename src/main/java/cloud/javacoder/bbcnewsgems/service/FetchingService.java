package cloud.javacoder.bbcnewsgems.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FetchingService {

    String getHtml(String url);
    List<String> getHeadlines(String attribute);
}
