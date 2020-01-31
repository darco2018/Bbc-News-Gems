package cloud.javacoder.bbcnewsgems.dictionary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = {Dictionary.class, DictionaryEntry.class, CSVToDictionaryEntryConverter.class})
public class CSVToDictionaryEntryConverterTest {

    private static String pathToFile = "dictionary5000.csv";

    @Test
    public void whenRedingCSVFile_fillsDictionaryWithEntries() throws IOException {
        Dictionary dictionary = new Dictionary();
        List<DictionaryEntry> entries = dictionary.getEntries();
        CSVToDictionaryEntryConverter converter = new CSVToDictionaryEntryConverter(dictionary);
        converter.parse(pathToFile);

        Assertions.assertThat(entries.size()).isEqualTo(5000);
        Assertions.assertThat(entries.get(1).getWord()).isEqualTo("be");
        Assertions.assertThat(entries.get(2).getPartOfSpeech()).isEqualTo("c");
        Assertions.assertThat(entries.get(4999).getWord()).isEqualTo("till");
        Assertions.assertThat(entries.get(4999).getFrequency()).isEqualTo(5079);
        Assertions.assertThat(entries.get(4999).getDispersion()).isEqualTo(0.92F);
    }
}
