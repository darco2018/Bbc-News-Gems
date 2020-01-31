package cloud.javacoder.bbcnewsgems.headlines;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
public class HeadlinesDTO {
    private String version;
    private ArrayList<FilteredHeadline> data;


}
