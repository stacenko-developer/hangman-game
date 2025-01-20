package backend.academy.hangman.consoleInput;

import backend.academy.hangman.constants.ExceptionTextValues;
import backend.academy.hangman.exception.IncorrectInputException;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConsoleReader {

    private static final String DIGITS_ONLY_REGEX = "^[0-9]+$";
    private static final String LETTER_REGEX = "^[А-Яа-яёЁ]$";

    private final InputScanner inputScanner;

    public ConsoleReader(InputScanner inputScanner) {
        this.inputScanner = Objects.requireNonNullElseGet(inputScanner, ConsoleInputScanner::new);
    }

    public ConsoleReader() {
        this.inputScanner = new ConsoleInputScanner();
    }

    public int readPositiveInteger(int minValue, int maxValue) {
        final String input = inputScanner.nextLine();

        if (isInvalidNumber(input, minValue, maxValue)) {
            throw new IncorrectInputException(ExceptionTextValues.INCORRECT_INPUT_EXCEPTION_TEXT);
        }

        return Integer.parseInt(input);
    }

    public char readLetter() {
        final String input = inputScanner.nextLine();

        if (isInvalidLetter(input)) {
            throw new IncorrectInputException(ExceptionTextValues.INCORRECT_INPUT_EXCEPTION_TEXT);
        }

        return input.charAt(0);
    }

    public String readLetterOrEnter() {
        final String input = inputScanner.nextLine();

        if (isInvalidLetter(input) && !input.isEmpty()) {
            throw new IncorrectInputException(ExceptionTextValues.INCORRECT_INPUT_EXCEPTION_TEXT);
        }

        return input;
    }

    private boolean isInvalidNumber(String input, int minValue, int maxValue) {
        try {
            return !input.matches(DIGITS_ONLY_REGEX)
                || Integer.parseInt(input) < minValue || Integer.parseInt(input) > maxValue;
        } catch (Exception ex) {
            return true;
        }
    }

    private boolean isInvalidLetter(String input) {
        return !input.matches(LETTER_REGEX);
    }
}
