package cloud.javacoder.bbcnewsgems.service.filtering;

import cloud.javacoder.bbcnewsgems.dictionary.Dictionary;
import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FilteringServiceTest_Mockito {

    List<String> headlines;
    @Mock
    private Dictionary dictionary;
    private FilteringService filteringService;
    private String lastWord = "ccc";
    private String wordInDictRange_2 = "eee";

    @BeforeEach
    public void init() {
        filteringService = new FilteringServiceImpl(dictionary);

        headlines = new ArrayList<String>();

        String headline_1 = "aaa bbb " + lastWord;
        headlines.add(headline_1);

        String headline_2 = "ddd " + wordInDictRange_2 + " fff";
        headlines.add(headline_2);
    }

    @Test
    public void givenWordInRange_ReturnsCorrectIndexOfThisWord() {

        given(this.dictionary.containsWord(lastWord)).willReturn(-1); // word out of filtered range
        //given(this.dictionary.containsWord(anyString())).willReturn(-1); // ok

        // NOT ok : you cannot use anyObject(), eq() methods outside of verified/stubbed method:
        //given(this.dictionary.containsWord(anyString())).willReturn(AdditionalMatchers.lt(0));

        int noOfInputHeadlines = this.headlines.size();

        List<FilteredHeadline> filteredHeadlines = filteringService.filter(headlines, 1, 5000);
        FilteredHeadline headline_1 = filteredHeadlines.get(0);

        Assertions.assertThat(filteredHeadlines.size()).isEqualTo(noOfInputHeadlines);

        String[] words = headline_1.getWords();
        int[] outOfRange = headline_1.getOutOfRangeWords();

        Assertions.assertThat(words.length).isEqualTo(this.headlines.get(0).split(" ").length);
        Assertions.assertThat(words[2]).isEqualTo(lastWord);

        Assertions.assertThat(outOfRange.length).isEqualTo(1); // actual: 0
        Assertions.assertThat(outOfRange[0]).isEqualTo(2); // lastWord // ArrayIndexOutOfBoundsException: 0

    }


}
