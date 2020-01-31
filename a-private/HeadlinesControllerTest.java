package cloud.javacoder.bbcnewsgems.headlines;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class HeadlinesControllerTest {

    private static String HEADLINES_URL = "/headlines";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenHeadlinesEndpoint_returnsOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL))
                .andExpect(status().isOk());
    }

    @Test
    public void whenHeadlinesEndpoint_returnsVersionNumber() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL))
                .andExpect(jsonPath("version").value("1.0"));
    }

    @Test
    public void whenHeadlinesEndpoint_returnsDataArray() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL))
                .andExpect(jsonPath("data").exists())
                .andExpect(jsonPath("data").isArray());
    }

    @Test
    public void whenHeadlinesEndpoint_returnsFilteredArrayAndSequenceArray() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL))
                .andExpect(jsonPath("$.data[*].sequence").exists())
                .andExpect(jsonPath("$.data[*].filtered").exists())
                .andExpect(jsonPath("$.data[*].sequence").isArray())
                .andExpect(jsonPath("$.data[*].filtered").isArray());
    }
/*
    @Test
    public void whenHeadlinesEndpoint_returnsNonEmptyArrays() throws Exception {
        //this.mockMvc.perform(MockMvcRequestBuilders.get(HEADLINES_URL)).andReturn();
               // .andExpect(content(). .json().)
    }*/
}
