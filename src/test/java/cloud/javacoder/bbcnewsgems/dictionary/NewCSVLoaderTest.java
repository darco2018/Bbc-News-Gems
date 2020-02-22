package cloud.javacoder.bbcnewsgems.dictionary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NewCSVLoaderTest {

    private CSVToDictionaryLoader loader;
    private String csvFile = "dictionary5000.csv";
    private Dictionary dict;

    @BeforeEach
    public void setUp() {
        dict = new Dictionary();
        loader = new CSVToDictionaryLoader(dict);
    }

    @Test
    public void loads5000entries() throws IOException {
        // act
        loader.load(csvFile);

        // assert
        Assertions.assertThat(dict.getDictionaryByRank().size()).isEqualTo(5000);
    }

    @Test
    public void loads4352entries() throws IOException {
        // act
        loader.loadIntoMap(csvFile);

        // assert
        Map<String, List<DictionaryEntry>> realDict = dict.getRealDictionary();

        Assertions.assertThat(realDict.size()).isEqualTo(4352); // no duplicates;
        System.out.println("Size is " + dict.getRealDictionary().size());

        //

        int wordsWithMultipleEntries = 0;
        int wordsWithSingleEntry = 0;
        for (String word : realDict.keySet()) {
            if (realDict.get(word).size() > 1) {
                wordsWithMultipleEntries++;
            } else {
                wordsWithSingleEntry++;
            }
        }
        // assert
        Assertions.assertThat(wordsWithMultipleEntries).isEqualTo(604);
        Assertions.assertThat(wordsWithSingleEntry).isEqualTo(4352 - 604);
    }
}
