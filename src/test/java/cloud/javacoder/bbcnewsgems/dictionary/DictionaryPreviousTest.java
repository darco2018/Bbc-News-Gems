package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = {DictionaryPrevious.class})
public class DictionaryPreviousTest {

    private static DictionaryEntry entry_1 = new DictionaryEntry();
    private DictionaryPrevious dictionaryPrevious = new DictionaryPrevious();


    @BeforeAll
    private static void createEntry() {
        entry_1.setRank(1);
        entry_1.setWord("the");
        entry_1.setPartOfSpeech("a");
        entry_1.setFrequency(22038615);
        entry_1.setDispersion(0.98F);
    }

    @Test
    public void whenAddEntry_thenOK() {
        log.info("About to add an entry: ");
        dictionaryPrevious.addEntry(entry_1);

        Assertions.assertThat(dictionaryPrevious.getDictionaryByRank().size()).isEqualTo(1);
    }

    @Test
    public void dictionaryEntryHasRequiredFields() {

        Assertions.assertThat(entry_1.getRank()).isEqualTo(1);
        Assertions.assertThat(entry_1.getWord()).isEqualTo("the");
        Assertions.assertThat(entry_1.getPartOfSpeech()).isEqualTo("a");
        Assertions.assertThat(entry_1.getFrequency()).isEqualTo(22038615);
        Assertions.assertThat(entry_1.getDispersion()).isEqualTo(0.98F);
    }

    @Test
    public void givenDictionaryContainsWord_returnsCorrectRank() {

        dictionaryPrevious.addEntry(entry_1);

        DictionaryEntry entry_2 = new DictionaryEntry();
        entry_2.setRank(2);
        entry_2.setWord("be");
        dictionaryPrevious.addEntry(entry_2);

        Assertions.assertThat(this.dictionaryPrevious.containsWord("the")).isEqualTo(1);
        Assertions.assertThat(this.dictionaryPrevious.containsWord("be")).isEqualTo(2);
    }

    @Test
    public void givenDictionaryDoesntContainWord_returnsNegative() {

        dictionaryPrevious.addEntry(entry_1);

        Assertions.assertThat(this.dictionaryPrevious.containsWord("notindictionary")).isEqualTo(-1);
    }

}
