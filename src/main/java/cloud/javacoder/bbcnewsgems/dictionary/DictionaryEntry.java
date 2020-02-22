package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.Data;

import java.util.Objects;

/*
*  Represents word data
*/
@Data
public class DictionaryEntry {

    private int rank;
    private String word;
    private String partOfSpeech;
    private int frequency;
    private float dispersion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryEntry that = (DictionaryEntry) o;
        return word.equals(that.word) &&
                partOfSpeech.equals(that.partOfSpeech);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, partOfSpeech);
    }
}
