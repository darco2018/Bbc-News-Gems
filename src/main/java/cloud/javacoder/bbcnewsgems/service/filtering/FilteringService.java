package cloud.javacoder.bbcnewsgems.service.filtering;

import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilteringService {

    List<FilteredHeadline> filter(List<String> headlines, int rangeStart, int rangeEnd);
}
