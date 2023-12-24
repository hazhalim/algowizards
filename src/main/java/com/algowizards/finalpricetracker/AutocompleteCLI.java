package com.algowizards.finalpricetracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class AutocompleteCLI {

    private static final String FILE_NAME = "wordlist.txt";
    private static final int FUZZY_TOLERANCE = 2;

    public static void main(String[] args) {
        List<String> wordList = loadWordList();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Type to start fuzzy autocomplete. Press 'q' to quit.");

        while (true) {
            System.out.print("Search: ");
            String userInput = scanner.nextLine().toLowerCase();

            if ("q".equals(userInput)) {
                System.out.println("Quitting...");
                saveWordList(wordList);
                break;
            }

            List<String> suggestions = fuzzyAutocomplete(userInput, wordList);

            System.out.println("Fuzzy Autocomplete Suggestions:");
            for (String suggestion : suggestions) {
                System.out.println(suggestion);
            }
        }

        scanner.close();
    }

    private static List<String> fuzzyAutocomplete(String query, List<String> words) {
        List<String> suggestions = new ArrayList<>();

        for (String word : words) {
            if (calculateLevenshteinDistance(word.toLowerCase(), query) <= FUZZY_TOLERANCE) {
                suggestions.add(word);
            }
        }

        return suggestions;
    }

    private static int calculateLevenshteinDistance(String s1, String s2) {
        int[][] distanceMatrix = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            distanceMatrix[i][0] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            distanceMatrix[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                distanceMatrix[i][j] = minimum(
                        distanceMatrix[i - 1][j] + 1,
                        distanceMatrix[i][j - 1] + 1,
                        distanceMatrix[i - 1][j - 1] + cost
                );
            }
        }

        return distanceMatrix[s1.length()][s2.length()];
    }

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static List<String> loadWordList() {
        List<String> wordList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(AutocompleteCLI.class.getClassLoader().getResourceAsStream(FILE_NAME))) {
            while (fileScanner.hasNextLine()) {
                wordList.add(fileScanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error loading word list. Using an empty list.");
        }
        return wordList;
    }

    private static void saveWordList(List<String> wordList) {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            for (String word : wordList) {
                writer.println(word);
            }
        } catch (Exception e) {
            System.out.println("Error saving word list.");
        }
    }
}