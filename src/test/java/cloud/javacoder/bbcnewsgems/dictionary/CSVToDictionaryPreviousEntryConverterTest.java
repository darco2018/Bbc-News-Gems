package cloud.javacoder.bbcnewsgems.dictionary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@SpringBootTest(classes = {DictionaryPrevious.class, DictionaryEntry.class, CSVToDictionaryLoader.class})
public class CSVToDictionaryPreviousEntryConverterTest {

    private static String pathToFile = "dictionary5000.csv";
    List<DictionaryEntry> dictionaryByRank;
    Map<String, DictionaryEntry> dictionaryByWord;
    private DictionaryPrevious dictionaryPrevious;
    private CSVToDictionaryLoader converter;

    @BeforeEach
    public void setUp() throws IOException {
        dictionaryPrevious = new DictionaryPrevious();
        converter = new CSVToDictionaryLoader(dictionaryPrevious);
        converter.load(pathToFile);

        dictionaryByRank = dictionaryPrevious.getDictionaryByRank();
        dictionaryByWord = dictionaryPrevious.getDictionaryByWord();
    }


    @Test
    public void whenReadingCSVFile_fillsDictionaryByRank() throws IOException {

        Assertions.assertThat(dictionaryByRank.size()).isEqualTo(5000);
        Assertions.assertThat(dictionaryByRank.get(1).getWord()).isEqualTo("be");
        Assertions.assertThat(dictionaryByRank.get(2).getPartOfSpeech()).isEqualTo("c");
        Assertions.assertThat(dictionaryByRank.get(4999).getWord()).isEqualTo("till");
        Assertions.assertThat(dictionaryByRank.get(4999).getFrequency()).isEqualTo(5079);
        Assertions.assertThat(dictionaryByRank.get(4999).getDispersion()).isEqualTo(0.92F);
    }

    @Test
    void givenDictionaryEntries_sortsByWordAscending() {
        // act
        List<DictionaryEntry> sortedByWord = this.dictionaryPrevious.sortEntriesByWord(dictionaryByRank);

        // assert
        Assertions.assertThat(sortedByWord.size()).isEqualTo(5000);
        Assertions.assertThat(sortedByWord.get(0).getWord()).isEqualTo("AIDS");
        Assertions.assertThat(sortedByWord.get(0).getPartOfSpeech()).isEqualTo("n");
        Assertions.assertThat(sortedByWord.get(4999).getWord()).isEqualTo("zone");
        Assertions.assertThat(sortedByWord.get(4999).getPartOfSpeech()).isEqualTo("n");

        Function<DictionaryEntry, String> fiveOccurrencesOfNo = entry -> entry.getWord() + "-" + entry.getPartOfSpeech();
        Assertions.assertThat(sortedByWord).extracting(fiveOccurrencesOfNo).contains("no-a", "no-r", "no-u", "no-p", "no-d");

        //this.dictionary.printSortedByWordAsc();
        Map<String, Integer> map = this.dictionaryPrevious.getFrequencyMap();
        System.out.println("Map is " + map.size()); // Words in dictionary without repetitions: 4352

        Map<String, Integer> repeated = this.dictionaryPrevious.getWordsWithFrequencyLargerThanOne();
        for (String word : repeated.keySet()) {
            int freq = repeated.get(word);
            System.out.printf("%d.%s\n", freq, word);
        }
        System.out.println(repeated.size());


        /*
        repeated.forEach( );

        Map<String, Integer> frequencyMap = new TreeMap<>();
        sortedByWord.stream().map(DictionaryEntry::getWord).forEach(word -> {
            if (frequencyMap.containsKey(word)) {
                int freq = frequencyMap.get(word);
                frequencyMap.put(word, ++freq);
            } else {
                frequencyMap.put(word, 1);
            }
        });
        System.out.println("Words in dictionary without repetitions: " + frequencyMap.size());
        // : 4352 entries(no duplicates!) = 3748 (freq: 1) +  604 (freq > 1)
        // --------------------------------------------------------------
        int occurrenceCount = 0;
        int countOfRepetitions = 0;
        int unrepeated =0;
        for (String word : frequencyMap.keySet()) {
            int freq = frequencyMap.get(word);

            if (freq > 1) {

                countOfRepetitions++;
            } else {
                unrepeated++;
            }

            occurrenceCount += freq;
        }

        System.out.println(">>>>>>> Number of repeated: " + countOfRepetitions); // 604
        System.out.println(">>>>>>> Number of unrepeated: " + unrepeated); // 3748
        System.out.println(">>>>>>> Number of occurences: " + occurrenceCount); // 5000*/
    }

    @Test
    public void whenReadingCSVFile_fillsDictionaryByWord() throws IOException {

        Assertions.assertThat(dictionaryByWord.size()).isEqualTo(5000);
       /* Assertions.assertThat(dictionaryByRank.get(1).getWord()).isEqualTo("be");
        Assertions.assertThat(dictionaryByRank.get(2).getPartOfSpeech()).isEqualTo("c");
        Assertions.assertThat(dictionaryByRank.get(4999).getWord()).isEqualTo("till");
        Assertions.assertThat(dictionaryByRank.get(4999).getFrequency()).isEqualTo(5079);
        Assertions.assertThat(dictionaryByRank.get(4999).getDispersion()).isEqualTo(0.92F);*/
    }


}
