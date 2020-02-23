package cloud.javacoder.bbcnewsgems.service.filtering;

import cloud.javacoder.bbcnewsgems.dictionary.DictionaryPrevious;
import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class FilteringServiceTest_Mockito {

    private List<String> headlines;
    @Mock
    private DictionaryPrevious dictionaryPrevious;
    private FilteringService filteringService;

    @BeforeEach
    public void init() {
        filteringService = new FilteringServiceImpl(this.dictionaryPrevious);
        this.headlines = new ArrayList<String>();
        this.headlines.add("aaa bbb ccc");
        this.headlines.add("ddd eee fff ggg");
        this.headlines.add("hhh");

        int notInRange = -1;
        doReturn(1, 100, notInRange, 99, notInRange, notInRange, 4, 1000)
                .when(this.dictionaryPrevious).containsWord(anyString());
    }

    @Test
    public void givenHeadlines_returnsSameNoOfHeadlines() {

        List<FilteredHeadline> outputHeadlines = this.filteringService.filter(this.headlines, 1, 5000);

        Assertions.assertThat(outputHeadlines.size()).isEqualTo(this.headlines.size());
    }

    @Test
    public void givenHeadlines_returnsEachHeadlineAsArrayOfWords() {

        List<FilteredHeadline> outputHeadlines = this.filteringService.filter(this.headlines, 1, 5000);

        // assert
        String[] wordsOfHeadline_1 = outputHeadlines.get(0).getWords();
        String[] wordsOfHeadline_2 = outputHeadlines.get(1).getWords();

        Assertions.assertThat(wordsOfHeadline_1.length).isEqualTo(this.headlines.get(0).split(" ").length);
        Assertions.assertThat(wordsOfHeadline_1).containsExactly("aaa", "bbb", "ccc");

        Assertions.assertThat(wordsOfHeadline_2.length).isEqualTo(this.headlines.get(1).split(" ").length); // ex 5
        Assertions.assertThat(wordsOfHeadline_2).containsExactly("ddd", "eee", "fff", "ggg");
    }

    @Test
    public void givenRangeLimits_whenWordsOutOfRangePresentInHeadlines_findsTheirIndexes() {

        //given(this.dictionary.containsWord("ccc")).willReturn(-1); // word out of filtered range
        //given(this.dictionary.containsWord(anyString())).willReturn(-1); // ok

        // NOT ok : you cannot use anyObject(), eq() methods outside of verified/stubbed method:
        //given(this.dictionary.containsWord(anyString())).willReturn(AdditionalMatchers.lt(0));

        // given(yourMock.yourMethod()).willReturn(1, 2, 3);
        //given(this.dictionary.containsWord("ccc")).willReturn(1,100, -1);

        // act
        List<FilteredHeadline> outputHeadlines = filteringService.filter(headlines, 1, 5000);

        // assert 1st headline
        FilteredHeadline headline_1 = outputHeadlines.get(0);
        int[] outOfRange = headline_1.getOutOfRangeWords();

        Assertions.assertThat(outOfRange.length).isEqualTo(1);
        Assertions.assertThat(outOfRange[0]).isEqualTo(2);

        // assert 2nd headline
        FilteredHeadline headline_2 = outputHeadlines.get(1);
        int[] outOfRange_2 = headline_2.getOutOfRangeWords();

        Assertions.assertThat(outOfRange_2.length).isEqualTo(2);
        Assertions.assertThat(outOfRange_2[0]).isEqualTo(1);
        Assertions.assertThat(outOfRange_2[1]).isEqualTo(2);
    }

    @Test
    public void givenRangeLimits_whenNoWordsOutOfRangePresentInHeadline_returnsEmptyArray() {

        // act
        List<FilteredHeadline> outputHeadlines = filteringService.filter(headlines, 1, 5000);

        FilteredHeadline headline_3 = outputHeadlines.get(2);
        int[] outOfRange_3 = headline_3.getOutOfRangeWords();

        Assertions.assertThat(outOfRange_3.length).isEqualTo(0);

    }
}
