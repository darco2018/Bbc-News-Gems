package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class Dictionary {

    @Getter
    private Map<String, List<DictionaryEntry>> dictionary = new TreeMap<>();

    public static Dictionary from(List<DictionaryEntry> entries) {
        return new Dictionary();
    }
}
