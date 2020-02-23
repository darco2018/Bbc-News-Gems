package cloud.javacoder.bbcnewsgems.service.filtering;

import cloud.javacoder.bbcnewsgems.dictionary.Dictionary;
import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FilteringServiceImpl implements FilteringService {

    private final Dictionary dictionary;

    public FilteringServiceImpl(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public List<FilteredHeadline> filter(List<String> headlines, int rangeStart, int rangeEnd) {
        log.info(">>>>>>>>>>>>> Starting filtering for " + headlines.size() + " headlines; range(" + rangeStart + ", " + rangeEnd);
        List<FilteredHeadline> list = new ArrayList<>();

        for (String headline : headlines) {
            String[] words = headline.split(" ");
            words = cleanUp(words);
            int[] outOfRangeWords = getOutOfRange(words);
            log.info("Found " + outOfRangeWords.length + " out of range words: " + Arrays.toString(outOfRangeWords));

            FilteredHeadline filteredHeadline = new FilteredHeadline();
            filteredHeadline.setWords(words);
            filteredHeadline.setOutOfRangeWords(outOfRangeWords);
            list.add(filteredHeadline);
        }

        return list;
    }

    private String[] cleanUp(final String[] arr) {

        List<String> input = Arrays.asList(arr);
        List<String> output = new ArrayList<>();
        for (String word : input) {
            if (!word.isEmpty()) {
                output.add(word.trim());
            }
        }
        return output.toArray(new String[output.size()]);
    }

    private int[] getOutOfRange(String[] words) {
        List<Integer> outOfRange = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            log.info("Testing word: " + words[i]);

            if (! dictionary.containsWord(words[i])) {
                log.info("This word(index) is NOT in range: " + i);
                outOfRange.add(i);
            } else {
                log.info("This word(index) is in range: " + i);

            }
        }

        return outOfRange.stream().mapToInt(i -> i).toArray();
    }
}
