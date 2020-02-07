package cloud.javacoder.bbcnewsgems.service.filtering;

import cloud.javacoder.bbcnewsgems.dictionary.Dictionary;
import cloud.javacoder.bbcnewsgems.dictionary.DictionaryEntry;
import cloud.javacoder.bbcnewsgems.headlines.FilteredHeadline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringServiceImpl implements FilteringService {

    private final Dictionary dictionary;

    public FilteringServiceImpl(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public List<FilteredHeadline> filter(List<String> headlines, int dictionaryStart, int dictionaryEnd) {

        List<FilteredHeadline> list = new ArrayList<>();

        for(String headline : headlines){
            String [] words = headline.split(" ");
            int[] rareWords = getFilteredWords(words);

            FilteredHeadline filteredHeadline = new FilteredHeadline();
            filteredHeadline.setSequence(words);
            filteredHeadline.setFiltered(rareWords);

            list.add(filteredHeadline);
        }

        return list;
    }

    private int[] getFilteredWords(String[] words) {
        List<Integer> indexesOfFilteredWords = new ArrayList<>();


        for(int i = 0; i < words.length; i ++){
            if(dictionary.getEntries().contains(words[i])){
                indexesOfFilteredWords.add(i);
            }
        }

        return indexesOfFilteredWords.stream().mapToInt(i->i).toArray();
    }
}
