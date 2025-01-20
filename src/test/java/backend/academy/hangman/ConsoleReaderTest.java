package backend.academy.hangman;

import backend.academy.hangman.exception.IncorrectInputException;
import backend.academy.hangman.consoleInput.InputScanner;
import backend.academy.hangman.consoleInput.ConsoleReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.util.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static backend.academy.hangman.constants.ExceptionTextValues.INCORRECT_INPUT_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsoleReaderTest extends CommonTest {

    @Mock
    private InputScanner inputScanner;

    @InjectMocks
    private ConsoleReader consoleReader;

    @ParameterizedTest
    @MethodSource("getArgumentsForReadPositiveInteger")
    public void readPositiveInteger_ShouldReturnPositiveInteger(String input, int minValue, int maxValue, int correctResult) {
        final int number;

        when(inputScanner.nextLine()).thenReturn(input);
        number = consoleReader.readPositiveInteger(minValue, maxValue);

        assertEquals(number, correctResult);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForReadPositiveIntegerByIncorrectInput")
    public void readPositiveNumberByIncorrectInput_ShouldThrowIncorrectInputException(String input) {
        final int defaultMinValue = 0;
        final int defaultMaxValue = 0;

        when(inputScanner.nextLine()).thenReturn(input);

        assertThatThrownBy(() -> {
            consoleReader.readPositiveInteger(defaultMinValue, defaultMaxValue);
        }).isInstanceOf(IncorrectInputException.class)
            .hasMessageContaining(INCORRECT_INPUT_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForReadPositiveIntegerByIncorrectRange")
    public void readPositiveNumberByIncorrectRange_ShouldThrowIncorrectInputException(String input, int minValue, int maxValue) {
        when(inputScanner.nextLine()).thenReturn(input);

        assertThatThrownBy(() -> {
            consoleReader.readPositiveInteger(minValue, maxValue);
        }).isInstanceOf(IncorrectInputException.class)
            .hasMessageContaining(INCORRECT_INPUT_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getLetters")
    public void readLetter_ShouldReturnLetter(char correctLetter) {
        final char letter;
        final String input = String.valueOf(correctLetter);

        when(inputScanner.nextLine()).thenReturn(input);
        letter = consoleReader.readLetter();

        assertEquals(letter, correctLetter);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForReadLetterByIncorrectInput")
    public void readLetterByIncorrectInput_ShouldThrowIncorrectInputException(String input) {
        when(inputScanner.nextLine()).thenReturn(input);

        assertThatThrownBy(() -> {
            consoleReader.readLetter();
        }).isInstanceOf(IncorrectInputException.class)
            .hasMessageContaining(INCORRECT_INPUT_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getLetters")
    public void readLetterOrEnter_ShouldReturnLetter(char correctLetter) {
        final String result;
        final String input = String.valueOf(correctLetter);

        when(inputScanner.nextLine()).thenReturn(input);
        result = consoleReader.readLetterOrEnter();

        assertEquals(result.charAt(0), correctLetter);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForReadLetterByIncorrectInput")
    public void readLetterOrEnterByIncorrectInput_ShouldThrowIncorrectInputException(String input) {
        when(inputScanner.nextLine()).thenReturn(input);

        assertThatThrownBy(() -> {
            consoleReader.readLetter();
        }).isInstanceOf(IncorrectInputException.class)
            .hasMessageContaining(INCORRECT_INPUT_EXCEPTION_TEXT);
    }

    @Test
    public void readLetterOrEnter_ReadEnter_ShouldReturnEmptyString() {
        final String input = "";
        final String result;

        when(inputScanner.nextLine()).thenReturn(input);
        result = consoleReader.readLetterOrEnter();

        assertEquals(result, input);
    }

    private static List<Object[]> getArgumentsForReadPositiveInteger() {
        final List<Object[]> testValues = new ArrayList<>();
        final int numbersCount = 50;

        for (int a = 0; a < numbersCount; a++) {
            for (int b = 0; b < numbersCount; b++) {
                testValues.add(new Object[] {
                    String.valueOf(a),
                    a - b,
                    a + b,
                    a
                });
            }
        }

        return testValues;
    }

    private static List<String> getArgumentsForReadPositiveIntegerByIncorrectInput() {
        final List<String> testValues = new ArrayList<>();
        final List<Pair<Integer, Integer>> incorrectSymbolsNumbersRanges = List.of(
            new Pair<>(32, 48),
            new Pair<>(58, 127)
        );

        for (Pair<Integer, Integer> range : incorrectSymbolsNumbersRanges) {
            for (int i = range.getKey(); i < range.getValue(); i++) {
                testValues.add(String.valueOf((char) i));
            }
        }

        return testValues;
    }

    private static List<Object[]> getArgumentsForReadPositiveIntegerByIncorrectRange() {
        final List<Object[]> testValues = new ArrayList<>();
        final int numbersCount = 50;

        for (int i = 0; i < numbersCount; i++) {
            for (int b = 0; b < i; b++) {
                testValues.add(new Object[] {
                    String.valueOf(i), b, i - 1
                });
            }
            for (int b = i + 1; b < numbersCount; b++) {
                testValues.add(new Object[] {
                    String.valueOf(i), b, numbersCount
                });
            }
        }

        return testValues;
    }
}
