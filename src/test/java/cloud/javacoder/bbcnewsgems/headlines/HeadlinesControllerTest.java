package cloud.javacoder.bbcnewsgems.headlines;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeadlinesController.class)
public class HeadlinesControllerTest {

    private static String HEADLINES_URL = "/headlines";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FetchingService fetchingService;

    @MockBean
    private FilteringService filteringService;

    @MockBean
    private ToHeadlinesDTOMapper toHeadlinesDTOMapper;

    @Test
    public void getHeadlines_ReturnsOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getHeadlines_returnsRequiredJsonPathsAndTypes() throws Exception {

        given(toHeadlinesDTOMapper.map(new ArrayList<FilteredHeadline>()))
                .willReturn(new HeadlinesDTO());

        mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("description").isString())
                .andExpect(jsonPath("version").isString())
                .andExpect(jsonPath("wordsRankedStartIncl").isNumber())
                .andExpect(jsonPath("wordsRankedEndIncl").isNumber())
                .andExpect(jsonPath("dataSize").isNumber())
                .andExpect(jsonPath("data").isArray());
    }

    @Test
    public void getHeadlines_returnsCorrectJSONValuesForAllPathsExcludingDataPath() throws Exception {

        HeadlinesDTO headlinesDTO = new HeadlinesDTO();
        headlinesDTO.setDescription("BBC headlines filtered against a range of ranked most common English words");
        headlinesDTO.setVersion("1.0.0");
        headlinesDTO.setWordsRankedStartIncl(4000);
        headlinesDTO.setWordsRankedEndIncl(5000);
        headlinesDTO.setDataSize(3);

        given(toHeadlinesDTOMapper.map(new ArrayList<FilteredHeadline>()))
                .willReturn(headlinesDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("description")
                        .value("BBC headlines filtered against a range of ranked most common English words"))
                .andExpect(jsonPath("version").value("1.0.0"))
                .andExpect(jsonPath("wordsRankedStartIncl").value(4000))
                .andExpect(jsonPath("wordsRankedEndIncl").value(5000))
                .andExpect(jsonPath("dataSize").value(3))
                .andExpect(jsonPath("data").hasJsonPath()); //  a value, possibly null, exists.
    }

    @Test
    public void getHeadlines_returnsCorrectNestedPathsOfDataPath() throws Exception {

        FilteredHeadline filteredHeadline = new FilteredHeadline();
        ArrayList<FilteredHeadline> list = new ArrayList<FilteredHeadline>();
        list.add(filteredHeadline); // [0] index

        HeadlinesDTO headlinesDTO = new HeadlinesDTO();
        headlinesDTO.setData(list);

        //  you have to call a method on the mocked object
        // the argument can be also new new ArrayList<FilteredHeadline>()
        given(toHeadlinesDTOMapper.map(anyList())) // !!!!
                .willReturn(headlinesDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("data").exists())
                // a non-null value, possibly an empty array or map, exists
                .andExpect(jsonPath("$.data[?(@.sequence)]").hasJsonPath())
                .andExpect(jsonPath("$.data[?(@.sequence)]").isArray())
                .andExpect(jsonPath("$.data[?(@.filtered)]").hasJsonPath()) // at any index
                .andExpect(jsonPath("$.data[0].filtered").hasJsonPath()) // at [0] index
                .andExpect(jsonPath("$.data[?(@.filtered)]").isArray())
                .andDo(print());
    }

    @Test
    public void whenOtherServicesPlugged_getHeadlines_returnsOK() throws Exception {

        // PROBLEM: those services dont have to be present in the controller
        // what is only tested is the presence of those services in tha app and their methods

        // both willReturn must contain either a Matcher or a concrete raw object
        given(fetchingService.getBBCHeadlines())
                .willReturn(new ArrayList<String>());

        given(filteringService.filter(new ArrayList<String>()))
                .willReturn(new ArrayList<FilteredHeadline>());

        given(toHeadlinesDTOMapper.map(new ArrayList<FilteredHeadline>()))
                .willReturn(new HeadlinesDTO());

        mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL))
                .andExpect(status().isOk());
    }

    @Test
    public void checkIfControllerHasServices() {
        // doesnt check if dependencies are injected & matched with fields
        HeadlinesController headlinesController = new HeadlinesController(fetchingService, filteringService, toHeadlinesDTOMapper);
        Assertions.assertThat(headlinesController).hasNoNullFieldsOrProperties();
    }
}