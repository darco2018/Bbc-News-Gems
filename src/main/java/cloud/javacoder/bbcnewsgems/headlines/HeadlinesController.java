package cloud.javacoder.bbcnewsgems.headlines;

import cloud.javacoder.bbcnewsgems.service.fetching.FetchingService;
import cloud.javacoder.bbcnewsgems.service.filtering.FilteringService;
import cloud.javacoder.bbcnewsgems.service.mapping.ToHeadlinesDTOMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HeadlinesController {

    private final FetchingService fetchingService;
    private final FilteringService filteringService;
    private final ToHeadlinesDTOMapper toHeadlinesDTOMapper;

    public HeadlinesController(FetchingService fetchingService, FilteringService filteringService, ToHeadlinesDTOMapper toHeadlinesDTOMapper) {
        this.fetchingService = fetchingService;
        this.filteringService = filteringService;
        this.toHeadlinesDTOMapper = toHeadlinesDTOMapper;
    }


    @GetMapping("/headlines")
    public HeadlinesDTO getFilteredHeadlines(){
        return this.toHeadlinesDTOMapper.map(new ArrayList<FilteredHeadline>());
    }
}
