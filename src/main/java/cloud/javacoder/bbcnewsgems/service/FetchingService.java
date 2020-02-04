package cloud.javacoder.bbcnewsgems.service;

import org.springframework.stereotype.Service;

@Service
public interface FetchingService {

    String getHtml(String url);

}
