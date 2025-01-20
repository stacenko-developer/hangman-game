package backend.academy.hangman;

import backend.academy.hangman.dto.HangmanGameState;
import backend.academy.hangman.enums.GameLevel;
import backend.academy.hangman.enums.WordsCategory;
import backend.academy.hangman.exception.IncorrectInputException;
import backend.academy.hangman.game.GameSettingsState;
import backend.academy.hangman.game.HangmanGame;
import backend.academy.hangman.consoleInput.InputScanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.math3.util.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static backend.academy.hangman.constants.ExceptionTextValues.INCORRECT_INPUT_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HangmanGameTest extends CommonTest {

    @Mock
    private InputScanner inputScanner;

    @Test
    public void readExitOption_ShouldCorrectlyRead() {
        final String exitOptionInput = "0";
        final HangmanGame hangmanGame = new HangmanGame(inputScanner, System.out);

        when(inputScanner.nextLine()).thenReturn(exitOptionInput);
        hangmanGame.start();
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForChooseCategoryByCorrectInputNumber")
    public void chooseCategoryByCorrectInputNumber_ShouldChooseCategory(String categoryNumberInput) {
        final HangmanGame hangmanGame = new HangmanGame(inputScanner, System.out);
        final int categoryNumber = Integer.parseInt(categoryNumberInput);
        final WordsCategory[] wordsCategories = WordsCategory.values();

        final String chooseCategoryOption = "1";
        final String exitOption = "0";

        when(inputScanner.nextLine())
            .thenReturn(chooseCategoryOption)
            .thenReturn(categoryNumberInput)
            .thenReturn(exitOption);

        hangmanGame.start();

        GameSettingsState gameSettingsState = new GameSettingsState(wordsCategories[categoryNumber], null);

        assertEquals(gameSettingsState, hangmanGame.getGameSettingsState());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForChooseLevelByCorrectInputNumber")
    public void chooseLevelByCorrectInputNumber_ShouldChooseLevel(String levelNumberInput) {
        final HangmanGame hangmanGame = new HangmanGame(inputScanner, System.out);
        final int levelNumber = Integer.parseInt(levelNumberInput);
        final GameLevel[] gameLevels = GameLevel.values();

        final String chooseCategoryOption = "2";
        final String exitOption = "0";

        when(inputScanner.nextLine())
            .thenReturn(chooseCategoryOption)
            .thenReturn(levelNumberInput)
            .thenReturn(exitOption);

        hangmanGame.start();

        GameSettingsState gameSettingsState = new GameSettingsState(null, gameLevels[levelNumber]);

        assertEquals(gameSettingsState, hangmanGame.getGameSettingsState());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForIncorrectInputInMainMenu")
    public void incorrectInputInMainMenu_ShouldThrowIncorrectInputException(String input) {
        final HangmanGame hangmanGame = new HangmanGame(inputScanner, System.out);
        final HangmanGameState firstState;
        final HangmanGameState secondState;

        when(inputScanner.nextLine()).thenReturn(input);

        firstState = new HangmanGameState(hangmanGame);
        assertThrowIncorrectInputExceptionInMenu(hangmanGame);
        secondState = new HangmanGameState(hangmanGame);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForIncorrectInputInMainMenu")
    public void incorrectInputInChoosingCategory_ShouldThrowIncorrectInputException(String input) {
        final String categoryOptionInput = "1";
        final HangmanGame hangmanGame = new HangmanGame(inputScanner, System.out);

        final HangmanGameState firstState;
        final HangmanGameState secondState;

        when(inputScanner.nextLine())
            .thenReturn(categoryOptionInput)
            .thenReturn(input);

        firstState = new HangmanGameState(hangmanGame);
        assertThrowIncorrectInputExceptionInMenu(hangmanGame);
        secondState = new HangmanGameState(hangmanGame);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForIncorrectInputInMainMenu")
    public void incorrectInputInChoosingLevel_ShouldThrowIncorrectInputException(String input) {
        final String levelOptionInput = "2";
        final HangmanGame hangmanGame = new HangmanGame(inputScanner, System.out);

        final HangmanGameState firstState;
        final HangmanGameState secondState;

        when(inputScanner.nextLine())
            .thenReturn(levelOptionInput)
            .thenReturn(input);

        firstState = new HangmanGameState(hangmanGame);
        assertThrowIncorrectInputExceptionInMenu(hangmanGame);
        secondState = new HangmanGameState(hangmanGame);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForReadLetterByIncorrectInput")
    public void makeGuessAttemptByIncorrectInput_ShouldThrowIncorrectInputException(String input) {
        final HangmanGame hangmanGame = new HangmanGame(inputScanner, System.out);
        final HangmanGameState firstState;
        final HangmanGameState secondState;

        when(inputScanner.nextLine())
            .thenReturn(input);

        firstState = new HangmanGameState(hangmanGame);
        assertThatThrownBy(() -> {
            hangmanGame.makeGuessAttempt();
        }).isInstanceOf(IncorrectInputException.class)
            .hasMessageContaining(INCORRECT_INPUT_EXCEPTION_TEXT);
        secondState = new HangmanGameState(hangmanGame);

        assertEquals(firstState, secondState);
    }

    private void assertThrowIncorrectInputExceptionInMenu(HangmanGame hangmanGame) {
        assertThatThrownBy(() -> {
            hangmanGame.readMenuOption();
        }).isInstanceOf(IncorrectInputException.class)
            .hasMessageContaining(INCORRECT_INPUT_EXCEPTION_TEXT);
    }

    private static List<String> getArgumentsForIncorrectInputInMainMenu() {
        final List<String> testValues = new ArrayList<>();
        final List<Pair<Integer, Integer>> incorrectSymbolsNumbersRanges = List.of(
            new Pair<>(32, 47),
            new Pair<>(58, 126),
            new Pair<>(1040, 1103)
        );

        for (Pair<Integer, Integer> range : incorrectSymbolsNumbersRanges) {
            for (int i = range.getKey(); i <= range.getValue(); i++) {
                char symbol = (char) i;
                testValues.add(String.valueOf(symbol));
            }
        }

        return testValues;
    }

    private static List<String> getArgumentsForChooseCategoryByCorrectInputNumber() {
        return getOptionsInput(WordsCategory.values().length - 1);
    }

    private static List<String> getArgumentsForChooseLevelByCorrectInputNumber() {
        return getOptionsInput(GameLevel.values().length - 1);
    }

    private static List<String> getOptionsInput(int size) {
        return IntStream.rangeClosed(1, size)
            .mapToObj(String::valueOf)
            .toList();
    }
}
