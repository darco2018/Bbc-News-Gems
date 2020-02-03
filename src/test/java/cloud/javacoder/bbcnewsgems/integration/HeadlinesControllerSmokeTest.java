package cloud.javacoder.bbcnewsgems.integration;

import cloud.javacoder.bbcnewsgems.headlines.HeadlinesController;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HeadlinesControllerSmokeTest {

    @Autowired
    HeadlinesController controller;

    @Test
    public void contextLoads() throws Exception{
        Assertions.assertThat(controller).isNotNull();
    }
}
