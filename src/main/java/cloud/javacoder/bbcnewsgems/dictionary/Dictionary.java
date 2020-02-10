package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Dictionary {

    @Getter
    List<DictionaryEntry> repeated = new ArrayList<>();
    @Getter
    private List<DictionaryEntry> dictionaryByRank = new ArrayList<>();
    @Getter
    private Map<String, DictionaryEntry> dictionaryByWord = new TreeMap<>();

    public void addEntry(DictionaryEntry dictionaryEntry) {
        // log.info("----------->> Adding dictionaryEntry for word: " + dictionaryEntry.getWord() );
        this.dictionaryByRank.add(dictionaryEntry);

        String word = dictionaryEntry.getWord();
        if (this.dictionaryByWord.containsKey(word)) {
            repeated.add(dictionaryEntry);
        }
        this.dictionaryByWord.put(dictionaryEntry.getWord(), dictionaryEntry);
    }

    public int containsWord(String word) {

        DictionaryEntry entry = this.dictionaryByWord.get(word);

        int answer = (entry == null) ? -1 : entry.getRank();
        log.info("Rank of word " + word + " is " + answer);
        return answer;
    }

    public List<DictionaryEntry> sortEntriesByWord(final List<DictionaryEntry> entries) {
         /*
           Comparator<DictionaryEntry> byWord = new Comparator<DictionaryEntry>() {
             @Override
             public int compare(DictionaryEntry e1, DictionaryEntry e2) {
                 return e1.getWord().compareTo(e2.getWord());
             }
         };

          Comparator<DictionaryEntry> byWord = (e1, e2) -> e1.getWord().compareTo(e2.getWord());

         */
        Comparator<DictionaryEntry> byWord = Comparator.comparing(DictionaryEntry::getWord);
        entries.sort(byWord);
        List<DictionaryEntry> entriesByWord = new ArrayList<>(entries);

        return entriesByWord;
    }

}
