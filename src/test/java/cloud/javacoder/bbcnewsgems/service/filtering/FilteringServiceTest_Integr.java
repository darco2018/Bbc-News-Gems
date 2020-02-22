package cloud.javacoder.bbcnewsgems.service.filtering;

import cloud.javacoder.bbcnewsgems.dictionary.CSVToDictionaryLoader;
import cloud.javacoder.bbcnewsgems.dictionary.Dictionary;
import cloud.javacoder.bbcnewsgems.dictionary.DictionaryEntry;
import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.doReturn;


public class FilteringServiceTest_Integr {

    private List<String> headlines;
    private Dictionary dictionary = new Dictionary();
    private CSVToDictionaryLoader loader = new CSVToDictionaryLoader(dictionary);
    private static String pathToFile = "dictionary5000.csv";
    private FilteringService filteringService;

    @BeforeEach
    public void init() throws IOException {
        loader.load(pathToFile);
        int size1 = dictionary.getDictionaryByRank().size();
        int size2 = dictionary.getDictionaryByWord().size();
        List<DictionaryEntry> repetedMap = dictionary.getRepeated();
        Collections.sort(repetedMap, (x, y) -> (x.getWord()).compareToIgnoreCase(y.getWord()) );
        repetedMap.forEach(System.out::println);
        //int intsizeOFRepeted = .size();




        filteringService = new FilteringServiceImpl(this.dictionary);
        this.headlines = new ArrayList<String>();
        String headlineWith_1_WordOutOfDictionary = "I am agastopia";
        String headlineWith_2_WordsOutOfDictionary = "Halfpace is better than jentacular";
        String headlineWith_3_WordsRankedBetween4000And5000 = "Laundry and apology are over four thousand Thanksgiving";
        this.headlines.add(headlineWith_1_WordOutOfDictionary);
        this.headlines.add(headlineWith_2_WordsOutOfDictionary);
        this.headlines.add(headlineWith_3_WordsRankedBetween4000And5000);
    }

    @Test
    public void givenHeadlines_returnsSameNoOfHeadlines() {

        List<FilteredHeadline> outputHeadlines = this.filteringService.filter(this.headlines, 1, 5000);

        Assertions.assertThat(outputHeadlines.size()).isEqualTo(this.headlines.size());
    }

    @Test
    public void givenHeadlines_returnsEachHeadlineAsArrayOfWordsRegardlessOfRange() {

        // act
        // range ouside dictionary
        List<FilteredHeadline> outputHeadlines = this.filteringService.filter(this.headlines, 1, 5000);

        // assert
        String[] wordsOfHeadline_1 = outputHeadlines.get(0).getWords();
        String[] wordsOfHeadline_2 = outputHeadlines.get(1).getWords();

        Assertions.assertThat(wordsOfHeadline_1.length).isEqualTo(this.headlines.get(0).split(" ").length);
        Assertions.assertThat(wordsOfHeadline_1).containsExactly("I am agastopia".split(" "));

        Assertions.assertThat(wordsOfHeadline_2.length).isEqualTo(this.headlines.get(1).split(" ").length);
        Assertions.assertThat(wordsOfHeadline_2).containsExactly("Halfpace is better than jentacular".split(" "));

        // act
        // range within dictionary
        outputHeadlines = this.filteringService.filter(this.headlines, 1000, 4000);

        // assert
        wordsOfHeadline_1 = outputHeadlines.get(0).getWords();
        wordsOfHeadline_2 = outputHeadlines.get(1).getWords();

        Assertions.assertThat(wordsOfHeadline_1.length).isEqualTo(this.headlines.get(0).split(" ").length);
        Assertions.assertThat(wordsOfHeadline_1).containsExactly("I am agastopia".split(" "));

        Assertions.assertThat(wordsOfHeadline_2.length).isEqualTo(this.headlines.get(1).split(" ").length);
        Assertions.assertThat(wordsOfHeadline_2).containsExactly("Halfpace is better than jentacular".split(" "));
    }

    @Test
    public void givenRangeLimitsWithinDictionary_whenWordsOutOfRangePresentInHeadlines_findsTheirIndexes() {

        // act
        List<FilteredHeadline> outputHeadlines = filteringService.filter(headlines, 1, 5000);

        // assert 1st headline
        FilteredHeadline headline_1 = outputHeadlines.get(0);
        int[] outOfRange = headline_1.getOutOfRangeWords();

        Assertions.assertThat(outOfRange.length).isEqualTo(1); // 3
        Assertions.assertThat(outOfRange[0]).isEqualTo(2);

        // assert 2nd headline
       /* FilteredHeadline headline_2 = outputHeadlines.get(1);
        int[] outOfRange_2 = headline_2.getOutOfRangeWords();

        Assertions.assertThat(outOfRange_2.length).isEqualTo(2);
        Assertions.assertThat(outOfRange_2[0]).isEqualTo(1);
        Assertions.assertThat(outOfRange_2[1]).isEqualTo(2);*/

        // assert 2nd headline
        /*FilteredHeadline headline_2 = outputHeadlines.get(1);
        int[] outOfRange_2 = headline_2.getOutOfRangeWords();

        Assertions.assertThat(outOfRange_2.length).isEqualTo(2);
        Assertions.assertThat(outOfRange_2[0]).isEqualTo(1);
        Assertions.assertThat(outOfRange_2[1]).isEqualTo(2);*/
    }


    @Test
    public void givenHeadlinesWithWordsOutOfDictionary_FindsTheirIndexes() {
      /*  int start = 1;
        int end = 5000;


        List<String> headlines = Arrays.asList(headlineWith1RareWord, headlineWith2RareWords);

        // act
        List<FilteredHeadline> filteredHeadlines = this.filteringService.filter(headlines, start, end);

        // assert
        FilteredHeadline filteredHeadline_1 = filteredHeadlines.get(0);
        String[] words = filteredHeadline_1.getWords();
        int[] indexesOfRareWords = filteredHeadline_1.getOutOfRangeWords();

        //String [] words = new String [] {"I", "am", "agastopia"};
        Assertions.assertThat(words)
                .hasSize(3)
                .containsExactly("I", "am", "agastopia");

        Assertions.assertThat(indexesOfRareWords)
                .hasSize(1)
                .contains(2); // "agastopia"

        FilteredHeadline filteredHeadline_2 = filteredHeadlines.get(1);
        String[] words_2 = filteredHeadline_2.getWords();
        int[] indexesOfRareWords_2 = filteredHeadline_2.getOutOfRangeWords();

        Assertions.assertThat(words_2)
                .hasSize(5)
                .containsExactly("Halfpace", "is", "better", "than", "jentacular");

        Assertions.assertThat(indexesOfRareWords_2)
                .hasSize(2)
                .contains(0, 4); // "Halfpace", ... , "jentacular"*/
    }
}
