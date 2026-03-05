import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Autocorrect
 * <p>
 * A command-line tool to suggest similar words when given one not in the dictionary.
 * </p>
 * @author Zach Blick
 * @author William Beesley
 */
public class Autocorrect {

    /**
     * Constucts an instance of the Autocorrect class.
     * @param words The dictionary of acceptable words.
     * @param threshold The maximum number of edits a suggestion can have.
     */
    public int edit_distance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] table = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            table[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            table[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    table[i][j] = table[i-1][j-1];
                }
                else {
                    table[i][j] = Math.min(table[i-1][j-1], Math.min(table[i-1][j], table[i][j-1]));
                }
            }
        }
        return table[n][m];
    }


    public Autocorrect(String[] words, int threshold) {
        for (int i = 0; i < words.length; i++) {
            if (edit_distance(words[i], dictionary[1]) <= threshold) {
                System.out.println(words[i]);
            }
        }
    }

    /**
     * Runs a test from the tester file, AutocorrectTester.
     * @param typed The (potentially) misspelled word, provided by the user.
     * @return An array of all dictionary words with an edit distance less than or equal
     * to threshold, sorted by edit distnace, then sorted alphabetically.
     */
    public String[] runTest(String typed) {

        return new String[0];
    }


    /**
     * Loads a dictionary of words from the provided textfiles in the dictionaries directory.
     * @param dictionary The name of the textfile, [dictionary].txt, in the dictionaries directory.
     * @return An array of Strings containing all words in alphabetical order.
     */
    private static String[] loadDictionary(String dictionary)  {
        try {
            String line;
            BufferedReader dictReader = new BufferedReader(new FileReader("dictionaries/" + dictionary + ".txt"));
            line = dictReader.readLine();

            // Update instance variables with test data
            int n = Integer.parseInt(line);
            String[] words = new String[n];

            for (int i = 0; i < n; i++) {
                line = dictReader.readLine();
                words[i] = line;
            }
            return words;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}