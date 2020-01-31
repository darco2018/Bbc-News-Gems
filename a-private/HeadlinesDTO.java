package cloud.javacoder.bbcnewsgems.headlines;

import lombok.Data;

@Data
public class HeadlinesDTO {

    private String version = "1.0";
    private String description;
    private FilteredSentence[] data;
}
