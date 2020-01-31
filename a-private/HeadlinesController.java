package cloud.javacoder.bbcnewsgems.headlines;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HeadlinesController {

    @GetMapping(value = "/headlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HeadlinesDTO>  getFilteredHeadlines() {

        // service:  HeadlinesProvider -> List<String> fetchHeadlinesFromBBC() -> each String is a headline ?
        // service:  Filter ->  List<FilteredHeadline>  filter(List<String>)   -> each string is broken into FilteredHeadline which has ["Brexit", "is", "clash"] [0, 2]
        // service:  ToDTOMapper ->  String map(List<FilteredHeadline> ) -> uses ObjectMapper to create representation od JSON string
/*
        HeadlinesDTO headlinesDTO = new HeadlinesDTO();
        headlinesDTO.setVersion("1.0");
        headlinesDTO.setDescription("BBC headlines with filtered less common words");

        FilteredSentence filteredSentence = new FilteredSentence();
        filteredSentence.setSequence(new String[] {"I", "am", "fine"});
        filteredSentence.setFiltered(new int[] {1,2});

        headlinesDTO.setData(new FilteredSentence[] {filteredSentence, filteredSentence, filteredSentence} );

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(headlinesDTO);
            log.info("ResultingJSONstring:");
            log.info(json);

            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HeadlinesDTO headlinesDTO = new HeadlinesDTO();

        ResponseEntity<HeadlinesDTO> response = new ResponseEntity<>(headlinesDTO, httpHeaders,HttpStatus.OK);
        return response;
    }

}
