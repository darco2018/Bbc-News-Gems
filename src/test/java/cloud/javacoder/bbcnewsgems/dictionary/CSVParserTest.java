package cloud.javacoder.bbcnewsgems.dictionary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CSVParserTest {

    private String csvFile = "dictionary5000.csv";
    private Dictionary dict;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void given5000linesOfWordData_parsesThemInto5000DictionaryEntries() throws IOException {
        // act
        List<DictionaryEntry> entries = CSVParser.parse(csvFile);

        // assert
        Assertions.assertThat(entries.size()).isEqualTo(5000);
    }

    @Test
    public void loads4352entries() throws IOException {
        //arrange
        List<DictionaryEntry> entries = CSVParser.parse(csvFile);

        // act
        Dictionary dictionary = Dictionary.from(entries);

        // assert
        Map<String, List<DictionaryEntry>> wordsByName = dictionary.getWordsByName();
        Assertions.assertThat(wordsByName.size()).isEqualTo(4352);

        int wordsWithMultipleEntries = 0;
        int wordsWithSingleEntry = 0;
        for (String word : wordsByName.keySet()) {
            if (wordsByName.get(word).size() > 1) {
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
