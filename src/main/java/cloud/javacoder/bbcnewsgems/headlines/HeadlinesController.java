package cloud.javacoder.bbcnewsgems.headlines;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeadlinesController {

    @GetMapping("/headlines")
    public HeadlinesDTO getFilteredHeadlines(){
        return new HeadlinesDTO();
    }
}
