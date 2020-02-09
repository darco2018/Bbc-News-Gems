package cloud.javacoder.bbcnewsgems.service.filtering;

import cloud.javacoder.bbcnewsgems.dictionary.Dictionary;
import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilteringServiceImpl implements FilteringService {

    private final Dictionary dictionary;

    public FilteringServiceImpl(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public List<FilteredHeadline> filter(List<String> headlines, int rangeStart, int rangeEnd) {

        List<FilteredHeadline> list = new ArrayList<>();

        for (String headline : headlines) {
            String[] words = headline.split(" ");
            int[] rareWords = getFilteredWords(words);

            FilteredHeadline filteredHeadline = new FilteredHeadline();
            filteredHeadline.setWords(words);
            filteredHeadline.getOutOfRangeWords();

            list.add(filteredHeadline);
        }

        return list;
    }

    private int[] getFilteredWords(String[] words) {
        List<Integer> indexesOfFilteredWords = new ArrayList<>();


        for (int i = 0; i < words.length; i++) {
            if (dictionary.containsWord(words[i]) >= 0) {
                indexesOfFilteredWords.add(i);
            }
        }

        return indexesOfFilteredWords.stream().mapToInt(i -> i).toArray();
    }
}
