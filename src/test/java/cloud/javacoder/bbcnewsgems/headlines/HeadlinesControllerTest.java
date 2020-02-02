package cloud.javacoder.bbcnewsgems.headlines;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HeadlinesController.class)
public class HeadlinesControllerTest {

    private static String HEADLINES_URL = "/headlines";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHeadlines_ReturnsOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL))
                .andExpect(status().isOk());
    }

    @Test
    public void getHeadlines_returnsRequiredJsonPathsAndTypes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL))
                .andExpect(jsonPath("description").isString())
                .andExpect(jsonPath("version").isString())
                .andExpect(jsonPath("wordsRankedStartIncl").isNumber())
                .andExpect(jsonPath("wordsRankedEndIncl").isNumber())
                .andExpect(jsonPath("dataSize").isNumber())
                .andExpect(jsonPath("data").isArray());
    }
}