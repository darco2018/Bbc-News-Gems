package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class CSVParser {
    public static List<DictionaryEntry> parse(String filePath) {

        List<DictionaryEntry> entries = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                String[] words = line.split(",");
                DictionaryEntry entry = getDictionaryEntry(words);
                entries.add(entry);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    private static DictionaryEntry getDictionaryEntry(String[] wordData) {

        DictionaryEntry entry = new DictionaryEntry();
        entry.setRank(Integer.valueOf(wordData[0]));
        entry.setWord(wordData[1]);
        entry.setPartOfSpeech(wordData[2]);
        entry.setFrequency(Integer.valueOf(wordData[3]));
        entry.setDispersion(Float.valueOf(wordData[4]));

        if (Integer.valueOf(wordData[0]) == 1) {
            log.info("Started loading the dictionary with the first entry: \n\t" + wordData[0] + ". " + entry);
        }

        if (Integer.valueOf(wordData[0]) == 5000) {
            log.info("Finished loading the dictionary with last entry: \n\t" + wordData[0] + ". " + entry);
        }

        return entry;
    }
}
