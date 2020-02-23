package cloud.javacoder.bbcnewsgems.dictionary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DictionaryTest {

    private static String csvFile = "dictionary5000.csv";
    private static List<DictionaryEntry> entries;
    private Dictionary dictionary;

    @BeforeAll
    public static void setUp() {
        entries = CSVParser.parse(csvFile);
    }

    @Test
    public void givenWordData_whenCreatingDictionary_createsCorrectNumberOfWordsWithSingleDefinitionsAndMultipleDefinitions() throws IOException {

        // act
        Dictionary dictionary = new Dictionary(entries);

        // assert
        Map<String, List<DictionaryEntry>> wordsByName = dictionary.getWordMap();

        int wordsWithMultipleDefinitions = 0;
        int wordsWithSingleDefinition = 0;
        for (String word : wordsByName.keySet()) {
            if (wordsByName.get(word).size() > 1) {
                wordsWithMultipleDefinitions++;
            } else {
                wordsWithSingleDefinition++;
            }
        }
        // assert
        Assertions.assertThat(wordsByName.size()).isEqualTo(4352);
        Assertions.assertThat(wordsWithMultipleDefinitions).isEqualTo(604);
        Assertions.assertThat(wordsWithSingleDefinition).isEqualTo(4352 - 604);
    }


    @Test
    public void givenMultipleDefinitionsOfWord_whenCreatingDictionary_allDefinitionsArePreserved() {

        // act
        Dictionary dictionary = new Dictionary(entries);

        // assert
        Map<String, List<DictionaryEntry>> wordsByName = dictionary.getWordMap();
        List<DictionaryEntry> danceDefintions = wordsByName.get("dance");
        Assertions.assertThat(danceDefintions.size()).isEqualTo(2);
        Assertions.assertThat(danceDefintions)
                .extracting("word")
                .containsOnly("dance");
        Assertions.assertThat(danceDefintions)
                .extracting("partOfSpeech")
                .containsOnly("n", "v");
    }

    @Test
    public void whenCallingContainWord_correctlyFindsWords(){
        // assert
        Dictionary dictionary = new Dictionary(entries);

        // act
        boolean containsThe = dictionary.containsWord("the");
       // boolean containsTheCapitalized = dictionary.containsWord("The");
       // boolean containsTheUppercase = dictionary.containsWord("THE");
        boolean containsNonsenseString = dictionary.containsWord("sajdhkadfl");

        //assert
        Assertions.assertThat(containsThe).isTrue();
       // Assertions.assertThat(containsTheCapitalized).isTrue();
       // Assertions.assertThat(containsTheUppercase).isTrue();
        Assertions.assertThat(containsNonsenseString).isFalse();
    }

    @Test
    @Disabled
    public void whenCallingContainWord_correctlyDealsWithCapitalizedAndDifferentCase(){
        /* TODO */
    }



}
