package org.example;

import org.example.interfaces.DictionaryInterface;
import org.example.interfaces.URLFetcherInterface;
import org.example.interfaces.WordExtractorInterface;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class SpellingChecker {
    URLFetcherInterface fetcher;
    WordExtractorInterface extractor;
    DictionaryInterface dictionary;

    public SpellingChecker(URLFetcherInterface fetcher, WordExtractorInterface extractor, DictionaryInterface dictionary) {
        this.fetcher=fetcher;
        this.extractor=extractor;
        this.dictionary=dictionary;
    }

    public SortedMap<String, Integer> check() throws IOException {

		// download the document content
		//
//		URLFetcher fetcher = new URLFetcher();
		String content = fetcher.fetch();

		// extract words from the content
		//
//		WordExtractor extractor = new WordExtractor();
		List<String> words = extractor.extract(content);

		// find spelling mistakes
		//
//		Dictionary dictionary = new Dictionary("dict.txt");
		SortedMap<String, Integer> mistakes = new TreeMap<>();

        for (String word : words) {
            if (!dictionary.isValidWord(word)) {
                if (mistakes.containsKey(word)) {
                    int oldCount = mistakes.get(word);
                    mistakes.put(word, oldCount + 1);
                } else {
                    mistakes.put(word, 1);
                }
            }
        }

		return mistakes;
	}
}