package cloud.javacoder.bbcnewsgems.headlines;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
public class HeadlinesDTO {
    private String description = "";
    private String version = "";
    private int wordsRankedStartIncl = 0;
    private int wordsRankedEndIncl = 0;
    private int dataSize = 0;
    private ArrayList<FilteredHeadline> data = new ArrayList<>();


}
