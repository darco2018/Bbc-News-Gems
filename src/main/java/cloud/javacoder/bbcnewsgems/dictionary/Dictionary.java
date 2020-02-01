package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Dictionary {

    private List<DictionaryEntry> entries = new ArrayList<>();

    public void addEntry(DictionaryEntry dictionaryEntry) {

        this.entries.add(dictionaryEntry);
    }
}
