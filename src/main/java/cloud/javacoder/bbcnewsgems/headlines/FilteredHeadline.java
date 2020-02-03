package cloud.javacoder.bbcnewsgems.headlines;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class FilteredHeadline implements Serializable {

    private String[] sequence;
    private int[] filtered;
}
