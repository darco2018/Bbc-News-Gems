package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Immutable dictionary
 */
@Slf4j
public class Dictionary {

    @Getter
    private Map<String, List<DictionaryEntry>> wordMap;

    public Dictionary(List<DictionaryEntry> entries){
        this.load(entries);
    }

    private void load(List<DictionaryEntry> entries) {

        TreeMap<String, List<DictionaryEntry>> map = new TreeMap<>();

        for(DictionaryEntry entry : entries){
            String word = entry.getWord();
            List<DictionaryEntry> givenWordEntries = map.getOrDefault(word, new ArrayList<>());
            givenWordEntries.add(entry);
            map.put(word,givenWordEntries);
        }
        this.wordMap = Collections.unmodifiableMap(map);
    }

    public boolean containsWord(String word) {

        return this.wordMap.containsKey(word);
    }

}
