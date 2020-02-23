package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

@Slf4j
public class Dictionary {

    @Getter
    private static Map<String, List<DictionaryEntry>> wordsByName = new TreeMap<>();

    private Dictionary(){}

    public static Dictionary from(List<DictionaryEntry> entries) {

        for(DictionaryEntry entry : entries){
            String word = entry.getWord();

            List<DictionaryEntry> givenWordEntries = wordsByName.getOrDefault(word, new ArrayList<>());
            givenWordEntries.add(entry);
            wordsByName.put(word,givenWordEntries);
        }
        return new Dictionary();
    }
}
