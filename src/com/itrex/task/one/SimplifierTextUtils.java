package com.itrex.task.one;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimplifierTextUtils {

    private final static Pattern ARTICLE_PATTERN = Pattern.compile("(\\b[T|t][H|h][E|e]\\b|\\b[A|a]\\b|\\b[A|a][N|n]\\b)");

    private final static Pattern CC_PATTERN = Pattern.compile("[C|c][C|c]");
    private final static String CC_REPLACEMENT = "kc";
    private final static Pattern CE_PATTERN = Pattern.compile("[C|c][E|e]");
    private final static String CE_REPLACEMENT = "se";
    private final static Pattern CI_PATTERN = Pattern.compile("[C|c][I|i]");
    private final static String CI_REPLACEMENT = "si";
    private final static Pattern C_PATTERN = Pattern.compile("[C|c]");
    private final static String C_REPLACEMENT = "k";

    private final static Pattern EE_PATTERN = Pattern.compile("[E|e][E|e]");
    private final static String EE_REPLACEMENT = "i";
    private final static Pattern OO_PATTERN = Pattern.compile("[O|o][O|o]");
    private final static String OO_REPLACEMENT = "u";

    private final static Pattern LAST_E_PATTERN = Pattern.compile("(e\\b)|(E\\b)");

    public static String fixArticle(String currentWord) {
//        Remove articles
        Matcher articleMatcher = ARTICLE_PATTERN.matcher(currentWord);

        return articleMatcher.replaceAll("");
    }

    public static String fixC(String currentWord) {
//        Remove "c" from the text
        Matcher ccMatcher = CC_PATTERN.matcher(currentWord);
        currentWord = ccMatcher.replaceAll(CC_REPLACEMENT);

        Matcher ceMatcher = CE_PATTERN.matcher(currentWord);
        currentWord = ceMatcher.replaceAll(CE_REPLACEMENT);

        Matcher ciMatcher = CI_PATTERN.matcher(currentWord);
        currentWord = ciMatcher.replaceAll(CI_REPLACEMENT);

        Matcher cMatcher = C_PATTERN.matcher(currentWord);

        return cMatcher.replaceAll(C_REPLACEMENT);
    }

    public static String fixE(String currentWord) {
//        Remove the letter āeā at the end of each word
        if (currentWord.length() >= 2) {
            Matcher lastEMatcher = LAST_E_PATTERN.matcher(currentWord);
            currentWord = lastEMatcher.replaceAll("");
        }

        return currentWord;
    }

    public static String fixDoubleLetters(String currentWord) {
//        Remove a double letter
        Matcher eeMatcher = EE_PATTERN.matcher(currentWord);
        currentWord = eeMatcher.replaceAll(EE_REPLACEMENT);

        Matcher ooMatcher = OO_PATTERN.matcher(currentWord);
        currentWord = ooMatcher.replaceAll(OO_REPLACEMENT);

        StringBuilder currentWordBuilder = new StringBuilder(currentWord);
        for (int j = 0; j < currentWordBuilder.length() - 1; j++) {
            if (Character.toLowerCase(currentWordBuilder.charAt(j)) == Character.toLowerCase(currentWordBuilder.charAt(j + 1))) {
                currentWordBuilder.deleteCharAt(j);
                --j;
            }
        }

        return currentWordBuilder.toString();
    }

}
