package cloud.javacoder.bbcnewsgems.service.filtering;

import cloud.javacoder.bbcnewsgems.dictionary.Dictionary;
import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class FilteringServiceUnitTest {

    private Dictionary dictionary = new Dictionary();
    private FilteringService filteringService = new FilteringServiceImpl(dictionary);

    @Test
    public void givenHeadlines_andDictionaryStartAndEnd_returnsFilteredSentences(){
        int start = 1;
        int end = 5000;

        String headlineWith1RareWord = "I am agastopia";
        String headlineWith2RareWords = "Halfpace is better than jentacular";
        List<String> headlines = Arrays.asList(headlineWith1RareWord, headlineWith2RareWords);

        // act
        List<FilteredHeadline> filteredHeadlines = this.filteringService.filter(headlines, start, end);

        // assert
        FilteredHeadline filteredHeadline_1 = filteredHeadlines.get(0);
        String[] words = filteredHeadline_1.getSequence();
        int[] indexesOfRareWords = filteredHeadline_1.getFiltered();

        //String [] words = new String [] {"I", "am", "agastopia"};
        Assertions.assertThat(words)
                .hasSize(3)
                .containsExactly("I", "am", "agastopia");

        Assertions.assertThat(indexesOfRareWords)
                .hasSize(1)
                .contains(2); // "agastopia"

        FilteredHeadline filteredHeadline_2 = filteredHeadlines.get(1);
        String[] words_2 = filteredHeadline_2.getSequence();
        int[] indexesOfRareWords_2 = filteredHeadline_2.getFiltered();

        Assertions.assertThat(words_2)
                .hasSize(5)
                .containsExactly("Halfpace", "is", "better", "than", "jentacular");

        Assertions.assertThat(indexesOfRareWords_2)
                .hasSize(2)
                .contains(0, 4); // "Halfpace", ... , "jentacular"
    }
}
