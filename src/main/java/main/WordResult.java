package main;

import java.util.Set;

public class WordResult {
    Set<String> wordDictionary;
    Integer maxWordLength;

    WordResult(Set<String> wordDictionary, Integer maxWordLength) {
        this.wordDictionary = wordDictionary;
        this.maxWordLength = maxWordLength;
    }
}