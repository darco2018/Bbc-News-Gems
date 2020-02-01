package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class CSVToDictionaryEntryConverter {

    private Dictionary dictionary;

    public CSVToDictionaryEntryConverter(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void parse(String pathToCSVFile) throws IOException {

        try (Stream<String> lines = Files.lines(Paths.get(pathToCSVFile))) {

            lines.forEach(line -> {
                String[] words = line.split(",");
                DictionaryEntry entry = getDictionaryEntry(words);
                this.dictionary.addEntry(entry);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DictionaryEntry getDictionaryEntry(String[] words) {

        DictionaryEntry entry = new DictionaryEntry();
        entry.setRank(Integer.valueOf(words[0]));
        entry.setWord(words[1]);
        entry.setPartOfSpeech(words[2]);
        entry.setFrequency(Integer.valueOf(words[3]));
        entry.setDispersion(Float.valueOf(words[4]));

        if (Integer.valueOf(words[0]) == 1) {
            log.info("Loading the dictionary with: \n\t" + words[0] + ". " + entry);
        }

        if (Integer.valueOf(words[0]) == 5000) {
            log.info("Finished loading the dictionary with: \n\t" + words[0] + ". " + entry);
        }

        return entry;
    }
}
