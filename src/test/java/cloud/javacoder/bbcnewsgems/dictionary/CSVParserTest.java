package cloud.javacoder.bbcnewsgems.dictionary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


/* consider this test:
 * //@SpringBootTest(classes = {Dictionary.class, DictionaryEntry.class, CSVParser.class})
 */
public class CSVParserTest {

    private static String csvFile = "dictionary5000.csv";
    private static List<DictionaryEntry> entries;

    @BeforeAll
    public static void setUp() {
        entries = CSVParser.parse(csvFile);
    }

    @Test
    public void given5000CSVLines_parsesEachLine() throws IOException {
        Assertions.assertThat(entries.size()).isEqualTo(5000);
    }

    @Test
    public void given5000CSVLines_parsesEachIntoDictionaryEntryCorrectly() throws IOException {

        Assertions.assertThat(entries.get(0).getWord()).isEqualTo("the");
        Assertions.assertThat(entries.get(1).getWord()).isEqualTo("be");
        Assertions.assertThat(entries.get(2).getPartOfSpeech()).isEqualTo("c");
        Assertions.assertThat(entries.get(4999).getWord()).isEqualTo("till");
        Assertions.assertThat(entries.get(4999).getFrequency()).isEqualTo(5079);
        Assertions.assertThat(entries.get(4999).getDispersion()).isEqualTo(0.92F);
    }

    @Test
    public void whenParsing_dictionaryEntryHasRequiredFields() {
        DictionaryEntry entry_1 = entries.get(0);

        Assertions.assertThat(entry_1.getRank()).isEqualTo(1);
        Assertions.assertThat(entry_1.getWord()).isEqualTo("the");
        Assertions.assertThat(entry_1.getPartOfSpeech()).isEqualTo("a");
        Assertions.assertThat(entry_1.getFrequency()).isEqualTo(22038615);
        Assertions.assertThat(entry_1.getDispersion()).isEqualTo(0.98F);
    }


}
