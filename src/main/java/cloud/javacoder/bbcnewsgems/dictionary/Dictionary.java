package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Dictionary {

    @Getter private List<DictionaryEntry> dictionaryByRank = new ArrayList<>();
    @Getter private Map<String, DictionaryEntry> dictionaryByWord = new TreeMap<>();

     public void addEntry(DictionaryEntry dictionaryEntry) {
        log.info("----------->> Adding dictionaryEntry for word: " + dictionaryEntry.getWord() );
        this.dictionaryByRank.add(dictionaryEntry);
        this.dictionaryByWord.put(dictionaryEntry.getWord(), dictionaryEntry);
    }

    public int containsWord(String word){
        log.info("Contains word: " + word + " ?");
        DictionaryEntry entry = this.dictionaryByWord.get(word);
        log.info("DictionaryEntry entry " + entry);

        return entry == null ? -1 : entry.getRank();
    }
}
