package cloud.javacoder.bbcnewsgems.service.fetching;

import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface FetchingService {

    String getHtml(URI url);
}
