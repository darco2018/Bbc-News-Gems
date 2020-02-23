package cloud.javacoder.bbcnewsgems.dictionary;

import java.io.IOException;
import java.util.*;

public class DictMain {

    public static void main(String[] args) {

        List<DictionaryEntry> entries = CSVParser.parse("dictionary5000.csv");
        Dictionary dictionary = new Dictionary(entries);
        boolean containsThe = dictionary.containsWord("the");



        // print dictionary by word

        // print dictionary by rank (CSVParser needed here)

        // check if word is contained in a range      eg dict.isInRange(word, start, end)

        //get word's rank         eg  dict.getRank(word) ; if multiple definitions, get with minus rank

        //get word's rank for a given part of speech  eg dict.getRank(word, "n")

        // get subsequence   eg dict.subsequence(1000,2000)


        // get repeated words

        //  get frequency map:  Map<String, Integer> word,frequency

        // LESS USEFUL:

        // sort List<DictionaryEntry> entries by word --> look below



        Map<String, List<DictionaryEntry>> wordMap = dictionary.getWordMap();

    }
}



/*

     * @return words and the number they a
     * For the 5000 word dictionary, the size of the map is 4352 entries(no duplicates!) = 3748 (freq: 1) +  604 (freq > 1)

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

*/







/*

    public List<DictionaryEntry> sortEntriesByWord(final List<DictionaryEntry> entries) {
         */
/*
           Comparator<DictionaryEntry> byWord = new Comparator<DictionaryEntry>() {
             @Override
             public int compare(DictionaryEntry e1, DictionaryEntry e2) {
                 return e1.getWord().compareTo(e2.getWord());
             }
         };

          Comparator<DictionaryEntry> byWord = (e1, e2) -> e1.getWord().compareTo(e2.getWord());

         *//*

        List<DictionaryEntry> entriesByWord = new ArrayList<>(entries);
        Comparator<DictionaryEntry> byWord = Comparator.comparing(DictionaryEntry::getWord);
        entriesByWord.sort(byWord);

        return entriesByWord;
    }
*/
