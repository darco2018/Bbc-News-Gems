package cloud.javacoder.bbcnewsgems.headlines;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
public class HeadlinesDTO implements Serializable {
    private String description = "";
    private String version = "";
    private int wordsRankedStartIncl = 0;
    private int wordsRankedEndIncl = 0;
    private int dataSize = 0;
    private List<FilteredHeadline> data = new ArrayList<>();


}
