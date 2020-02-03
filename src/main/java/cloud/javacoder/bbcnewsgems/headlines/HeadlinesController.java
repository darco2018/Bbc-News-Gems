package cloud.javacoder.bbcnewsgems.headlines;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HeadlinesController {

    private final  ToHeadlinesDTOMapper toHeadlinesDTOMapper;

    public HeadlinesController(ToHeadlinesDTOMapper toHeadlinesDTOMapper) {
        this.toHeadlinesDTOMapper = toHeadlinesDTOMapper;
    }

    @GetMapping("/headlines")
    public HeadlinesDTO getFilteredHeadlines(){
        return this.toHeadlinesDTOMapper.map(new ArrayList<FilteredHeadline>());
    }
}
