package cloud.javacoder.bbcnewsgems.integration;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc // doesn't start the server but instatntiates the whole context (@WebMvcTest will only instantiate the web layer)
class HeadlinesControllerSanityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sanityCheck() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/headlines"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("")); // returning null, results in "" in json
        //.andExpect(MockMvcResultMatchers.content(). .string(containsString("BBC")));
    }

}
