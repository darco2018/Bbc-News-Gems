package cloud.javacoder.bbcnewsgems.service.mapping;

import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;
import cloud.javacoder.bbcnewsgems.headlines.HeadlinesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToHeadlinesDTOMapper {

    private static final String DESCRIPTION = "BBC headlines filtered against a range of ranked most common English words";


    public HeadlinesDTO map(List<FilteredHeadline> filteredHeadlines) {
        //HeadlinesDTO headlinesDTO = new HeadlinesDTO();
       // headlinesDTO.setDescription("HeadlinesDTO");
        return null;
    }
}
