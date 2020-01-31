package cloud.javacoder.bbcnewsgems.headlines;

import lombok.Data;

@Data
public class FilteredSentence {

    String[] sequence;
    int[] filtered;
}
