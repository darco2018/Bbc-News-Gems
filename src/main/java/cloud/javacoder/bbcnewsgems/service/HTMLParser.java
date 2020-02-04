package cloud.javacoder.bbcnewsgems.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HTMLParser {

    List<String> parseHeadlines(String html);

}
