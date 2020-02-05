package cloud.javacoder.bbcnewsgems.service.fetching;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FetchingService {

    String getHtml(String url);
}
