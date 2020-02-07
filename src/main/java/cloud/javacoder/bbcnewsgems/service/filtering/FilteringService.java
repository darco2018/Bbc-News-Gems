package cloud.javacoder.bbcnewsgems.service.filtering;

import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;

import java.util.List;

public interface FilteringService {

    List<FilteredHeadline> filter(List<String> headlines, int dictionaryStart, int dictionaryEnd);
}
