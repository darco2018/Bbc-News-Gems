package cloud.javacoder.bbcnewsgems.dictionary;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class CSVToDictionaryLoader {

    private Dictionary dictionary;

    public CSVToDictionaryLoader(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Loads each line of CSV file into a DictionaryEntry
     */
    public void load(String pathToCSVFile) throws IOException {

        try (Stream<String> lines = Files.lines(Paths.get(pathToCSVFile))) {

            lines.forEach(line -> {
                String[] words = line.split(",");
                DictionaryEntry entry = this.getDictionaryEntry(words);
                this.dictionary.addEntry(entry);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadIntoMap(String pathToCSVFile) throws IOException {

        try(Stream<String> lines = Files.lines(Paths.get(pathToCSVFile))) {
            lines.forEach(line -> {
                String[] words = line.split(",");
                DictionaryEntry entry = this.getDictionaryEntry(words);
                Map<String, List<DictionaryEntry>>  map = this.dictionary.getRealDictionary();
                String word = entry.getWord();

                List<DictionaryEntry> entries = map.getOrDefault(word, new ArrayList<>());
                /*if(map.containsKey(word)){
                    entries = map.get(word);
                } else {
                    entries = new ArrayList<>();
                }*/

                entries.add(entry);
                map.put(word,entries);

            });
        }
    }

    private DictionaryEntry getDictionaryEntry(String[] wordData) {

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
