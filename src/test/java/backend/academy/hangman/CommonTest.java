package backend.academy.hangman;

import org.apache.commons.math3.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CommonTest {

    protected static List<String> getArgumentsForReadLetterByIncorrectInput() {
        final List<String> testValues = new ArrayList<>();

        final int minNonLetterNumber = 32;
        final int maxNonLetterNumber = 127;

        final int minLetterNumber = 1040;
        final int maxLetterNumber = 1103;
        final List<Pair<Integer, Integer>> ranges = List.of(
            new Pair<>(minNonLetterNumber, maxNonLetterNumber),
            new Pair<>(minLetterNumber, maxLetterNumber)
        );

        for (int i = minNonLetterNumber; i <= maxNonLetterNumber; i++) {
            testValues.add(String.valueOf((char) i));
        }
        for (Pair<Integer, Integer> range : ranges) {
            for (int i = range.getKey(); i < range.getValue(); i++) {
                final char symbol = (char) i;
                final String input = new String(new char[]{symbol, symbol});

                testValues.add(input);
            }
        }

        return testValues;
    }

    protected static List<Character> getArgumentsForGuessLetterByIncorrectSymbol() {
        final int minLetterNumber = 33;
        final int maxLetterNumber = 127;

        return getSymbolsByRange(minLetterNumber, maxLetterNumber);
    }

    protected static List<Character> getLetters() {
        final int minLetterNumber = 1040;
        final int maxLetterNumber = 1103;

        return getSymbolsByRange(minLetterNumber, maxLetterNumber);
    }

    private static List<Character> getSymbolsByRange(int minLetterNumber, int maxLetterNumber) {
        return IntStream.rangeClosed(minLetterNumber, maxLetterNumber)
            .mapToObj(i -> (char) i)
            .toList();
    }
}
