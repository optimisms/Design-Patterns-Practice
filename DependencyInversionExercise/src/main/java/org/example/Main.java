package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.SortedMap;

public class Main {
	public static void main(String[] args) {
		try {
			URL url = new URL(args[0]);
			SpellingChecker checker = new SpellingChecker(new URLFetcher(url), new WordExtractor(), new Dictionary("dict.txt"));
			SortedMap<String, Integer> mistakes = checker.check();
			System.out.println(mistakes);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}