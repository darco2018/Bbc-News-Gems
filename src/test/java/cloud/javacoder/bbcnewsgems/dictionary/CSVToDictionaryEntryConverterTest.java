package cloud.javacoder.bbcnewsgems.dictionary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@SpringBootTest(classes = {Dictionary.class, DictionaryEntry.class, CSVToDictionaryLoader.class})
public class CSVToDictionaryEntryConverterTest {

    private static String pathToFile = "dictionary5000.csv";
    private Dictionary dictionary;
    private CSVToDictionaryLoader converter;
    List<DictionaryEntry> dictionaryByRank;
    Map<String, DictionaryEntry> dictionaryByWord;

    @BeforeEach
    public void setUp() throws IOException {
        dictionary = new Dictionary();
        converter = new CSVToDictionaryLoader(dictionary);
        converter.parse(pathToFile);

        dictionaryByRank = dictionary.getDictionaryByRank();
        dictionaryByWord = dictionary.getDictionaryByWord();
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

    @Test void givenDictionaryEntries_sortsByWordAscending(){
        // act
        List<DictionaryEntry> sortedByWord = this.dictionary.sortEntriesByWord(dictionaryByRank);

        // assert
        Assertions.assertThat(sortedByWord.size()).isEqualTo(5000);
        Assertions.assertThat(dictionaryByRank.get(0).getWord()).isEqualTo("AIDS");
        Assertions.assertThat(dictionaryByRank.get(0).getPartOfSpeech()).isEqualTo("n");
        Assertions.assertThat(dictionaryByRank.get(4999).getWord()).isEqualTo("zone");
        Assertions.assertThat(dictionaryByRank.get(4999).getPartOfSpeech()).isEqualTo("n");

        Function<DictionaryEntry, String> fiveOccurrencesOfNo = entry -> entry.getWord() + "-" + entry.getPartOfSpeech() ;
        Assertions.assertThat(dictionaryByRank).extracting(fiveOccurrencesOfNo).contains("no-a", "no-r", "no-u", "no-p", "no-d");

        Map<String, Integer> frequencyMap = new HashMap<>();
        sortedByWord.forEach(entry-> {
           System.out.printf( "%s,%s\n", entry.getWord(), entry.getPartOfSpeech());

            /*
           String word = entry.getWord();
            if(frequencyMap.containsKey(word)){
                int freq = frequencyMap.get(word);
                frequencyMap.put(word, ++freq);
            } else {
                frequencyMap.put(word, 1);
            }*/
        });
        // --------------------------------------------------------------
       /* int counter = 0;
        for(String word : frequencyMap.keySet()){
            int freq = frequencyMap.get(word);

            if(freq > 1){
                System.out.printf("%d.%s\n", freq, word);
                counter++;
            }
        }

        System.out.println(">>>>>>> Number of repeated words: " + counter);
*/
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
