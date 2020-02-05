package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class Dictionary {

    private List<DictionaryEntry> entries = new ArrayList<>();
    private Map<String, DictionaryEntry> dictionary = new HashMap<>();

    public void addEntry(DictionaryEntry dictionaryEntry) {
        log.info("----------->> Adding dictionaryEntry for word: " + dictionaryEntry.getWord() );
        this.entries.add(dictionaryEntry);
        this.dictionary.put(dictionaryEntry.getWord(), dictionaryEntry);
    }

    public int containsWord(String word){
        log.info("Contains word: " + word + " ?");
        DictionaryEntry entry = this.dictionary.get(word);
        log.info("DictionaryEntry entry " + entry);

        if(entry == null){
            return -1;
        } else {
            return entry.getRank();
        }

    }
}
