package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.Data;

/*
* Maps dictionary data from csv file
*/
@Data
public class DictionaryEntry {

    private int rank;
    private String word;
    private String partOfSpeech;
    private int frequency;
    private float dispersion;

}
