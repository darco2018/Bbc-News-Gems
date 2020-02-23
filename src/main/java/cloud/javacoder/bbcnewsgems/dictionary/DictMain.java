package cloud.javacoder.bbcnewsgems.dictionary;

import java.io.IOException;
import java.util.List;

public class DictMain {

    public static void main(String[] args) {

        List<DictionaryEntry> entries = CSVParser.parse("dictionary5000.csv");
        Dictionary dictionary = Dictionary.from(entries);

        /*Dictionary dictionary = new Dictionary();
        CSVToDictionaryLoader loader = new CSVToDictionaryLoader(dictionary);
        try {
            loader.load("dictionary5000.csv");
            loader.loadIntoMap("dictionary5000.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Loaded %s entries %n",  dictionary.getDictionaryByRank().size());
        System.out.printf("Loaded %s entries",  dictionary.getRealDictionary().size());*/
    }
}
