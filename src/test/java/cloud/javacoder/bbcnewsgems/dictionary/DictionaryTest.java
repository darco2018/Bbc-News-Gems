package cloud.javacoder.bbcnewsgems.dictionary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Dictionary.class})
public class DictionaryTest {

    private DictionaryEntry dictionaryEntry;

    @BeforeAll
    private void createEntry() {
        dictionaryEntry.setRank(1);
        dictionaryEntry.setWord("the");
        dictionaryEntry.setPartOfSpeech("a");
        dictionaryEntry.setFrequency(22038615);
        dictionaryEntry.setDispersion(0.98F);
    }

    @Test
    public void whenAddEntry_thenOK() {
        Dictionary dictionary = new Dictionary();
        dictionary.addEntry(dictionaryEntry);

        Assertions.assertThat(dictionary.getEntries().size()).isEqualTo(1);
    }

    @Test
    public void dictionaryEntryHasRequiredFields() {

        Assertions.assertThat(dictionaryEntry.getRank()).isEqualTo(1);
        Assertions.assertThat(dictionaryEntry.getWord()).isEqualTo("the");
        Assertions.assertThat(dictionaryEntry.getPartOfSpeech()).isEqualTo("a");
        Assertions.assertThat(dictionaryEntry.getFrequency()).isEqualTo(22038615);
        Assertions.assertThat(dictionaryEntry.getDispersion()).isEqualTo(0.98F);
    }

}
