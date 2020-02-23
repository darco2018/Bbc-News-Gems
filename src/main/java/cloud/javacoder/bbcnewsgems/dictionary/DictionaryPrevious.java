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
public class DictionaryPrevious {

    @Getter
    private Map<String, List<DictionaryEntry>> realDictionary = new TreeMap<>();


    @Getter
    List<DictionaryEntry> repeated = new ArrayList<>();
    @Getter
    private List<DictionaryEntry> dictionaryByRank = new ArrayList<>();
    @Getter
    private Map<String, DictionaryEntry> dictionaryByWord = new TreeMap<>();

    /**
     * Creates a list of words sorted by rank, creates a map of words sorted by word
     * and also creates a list of repeated words eg dance - noun & verb
     */
    public void addEntry(DictionaryEntry dictionaryEntry) {




        this.dictionaryByRank.add(dictionaryEntry);

        // creates list of repeated words (eg dance - verb & noun)
        String word = dictionaryEntry.getWord();
        if (this.dictionaryByWord.containsKey(word)) {
            repeated.add(dictionaryEntry);
        }

        // creates inverted index - dictionary by word tree map
        this.dictionaryByWord.put(word, dictionaryEntry);
    }

    /**
     * @return Returns the rank of the word, or if the word hasn't been found, returns -1.
     */
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
        List<DictionaryEntry> entriesByWord = new ArrayList<>(entries);
        Comparator<DictionaryEntry> byWord = Comparator.comparing(DictionaryEntry::getWord);
        entriesByWord.sort(byWord);

        return entriesByWord;
    }

    public void printSortedByWordAsc() {
        List<DictionaryEntry> sortedByWord = this.sortEntriesByWord(this.dictionaryByRank);
        sortedByWord.forEach(entry -> {
            System.out.printf("%s,%s\n", entry.getWord(), entry.getPartOfSpeech());
        });
    }

    /**
     * @return words and the number they a
     * For the 5000 word dictionary, the size of the map is 4352 entries(no duplicates!) = 3748 (freq: 1) +  604 (freq > 1)
     */
    //
    public Map<String, Integer> getFrequencyMap() {
        Map<String, Integer> frequencyMap = new TreeMap<>();
        //List<DictionaryEntry> sortedByWord = this.sortEntriesByWord(this.dictionaryByRank);

        this.dictionaryByRank.stream().map(DictionaryEntry::getWord).forEach(word -> {
            if (frequencyMap.containsKey(word)) {
                int freq = frequencyMap.get(word);
                frequencyMap.put(word, ++freq);
            } else {
                frequencyMap.put(word, 1);
            }
        });

        return frequencyMap;
    }

    /**
     * @return words which appear in the dictionary more than once (for example: "dance" appears twice: as a noun and as a verb)
     * The map stores the number each word appears in the 5000 dictionary
     * For the 5000 word dictionary, the size of the map is 604 words
     */
    public Map<String, Integer> getWordsWithFrequencyLargerThanOne(){
        Map<String, Integer> frequencyMap = this.getFrequencyMap();
        Map<String, Integer> frequencyLargerThanOne = new HashMap<>();

        for (String word : frequencyMap.keySet()) {
            int freq = frequencyMap.get(word);

            if (freq > 1) {
                frequencyLargerThanOne.put(word, freq);
            }
        }

        return frequencyLargerThanOne;
    }

}
