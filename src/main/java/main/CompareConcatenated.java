package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CompareConcatenated {

    public void concateResult(String pathToFile) {
        try {
            WordResult result = readFile(pathToFile);
            Set<String> wordDict = result.wordDictionary;
            Integer maxWordLen = result.maxWordLength;
            System.out.println("Longest concatenated word is : " + findAllConcatenatedWords(wordDict,
                    maxWordLen).stream().max(String ::compareTo).get()
                    + "\nSecond concatenated word is : " + findAllConcatenatedWords(wordDict,
                    maxWordLen).get(2)
                    + "\nTotal concatenated word : "
                    + findAllConcatenatedWords(wordDict,
                    maxWordLen).size() + "  \n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<String> findAllConcatenatedWords(Set<String> wordDict, Integer maxWordLen) {
        List<String> сoncatenatedWords = new ArrayList();
        for (String word : wordDict) {
            if (concatenateValid(wordDict, word, maxWordLen)) {
                сoncatenatedWords.add(word);
            }
        }
        return сoncatenatedWords;
    }

    private boolean concatenateValid(final Set<String> wordDict, String str, int maxWordLen) {
        Set<String> wordDictCopy = new HashSet(wordDict);
        wordDictCopy.remove(str); //delete word if this word not concatenate
        int n = str.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i && j <= maxWordLen; j++) {
                if (!dp[i - j])
                    continue;

                String word = str.substring(i - j, i);
                if (wordDictCopy.contains(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public WordResult readFile(String path) throws Exception {
        Set<String> wordDict = new HashSet<>();
        int maxWordLen = Integer.MIN_VALUE;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            try {
                String line = bufferedReader.readLine();

                while (line != null) {
                    String word = new String(line);
                    maxWordLen = (maxWordLen > word.length()) ? maxWordLen : word.length();
                    if (word.length() != 0)
                        wordDict.add(word.trim());
                    line = bufferedReader.readLine();
                }
            } finally {
                bufferedReader.close();
            }
        } finally {
        }
        WordResult result = new WordResult(wordDict, maxWordLen);
        return result;
    }
}
