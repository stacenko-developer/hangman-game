package backend.academy.hangman;

import backend.academy.hangman.dto.GameSessionState;
import backend.academy.hangman.enums.GameStatus;
import backend.academy.hangman.exception.DuplicateLetterException;
import backend.academy.hangman.exception.IncorrectGameStatusException;
import backend.academy.hangman.exception.IncorrectLetterException;
import backend.academy.hangman.exception.IncorrectWordLengthException;
import backend.academy.hangman.game.GameSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.hangman.constants.ConstValues.HANGMAN_STAGES;
import static backend.academy.hangman.constants.ConstValues.HINTERS;
import static backend.academy.hangman.constants.ExceptionTextValues.GAME_HAS_ALREADY_ENDED_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.GAME_HAS_ALREADY_STARTED_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.INCORRECT_LETTERS_IN_WORDS_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.INCORRECT_LETTER_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.INCORRECT_WORD_LENGTH_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.LETTER_HAS_ALREADY_INPUTTED_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.NULL_CHOSEN_WORD_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSessionTest extends CommonTest {

    @Test
    public void createGameSessionByConstructor_ShouldCreateGameSession() {
        final GameSession gameSession = new GameSession();

        assertEquals(gameSession.gameStatus(), GameStatus.BEGIN_WAITING);
        assertEquals(GameSession.MAX_ATTEMPTS(), gameSession.remainingAttempts());
        assertEquals(gameSession.getHangmanStage(), HANGMAN_STAGES.getFirst());

        assertNotNull(gameSession.getGuessedWord());
        assertTrue(gameSession.getGuessedWord().isEmpty());
        assertNotNull(gameSession.inputLetters());
        assertTrue(gameSession.inputLetters().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForStartGame")
    public void startGame_ShouldStartGame(String chosenWord) {
        final GameSession gameSession = new GameSession();
        final String symbol = "_";
        final String guessedWord = symbol.repeat(chosenWord.length());

        gameSession.startGame(chosenWord);

        assertEquals(gameSession.gameStatus(), GameStatus.IN_PROCESS);
        assertEquals(gameSession.getGuessedWord(), guessedWord);
        assertEquals(GameSession.MAX_ATTEMPTS(), gameSession.remainingAttempts());
        assertEquals(gameSession.getHangmanStage(), HANGMAN_STAGES.getFirst());

        assertNotNull(gameSession.inputLetters());
        assertTrue(gameSession.inputLetters().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getWords")
    public void startGameSeveralTimes_ShouldThrowIncorrectGameStatusException(String word) {
        final GameSession gameSession = new GameSession();
        final GameSessionState firstState;
        final GameSessionState secondState;

        gameSession.startGame(word);

        firstState = new GameSessionState(gameSession);
        assertThatThrownBy(() -> {
            gameSession.startGame(word);
        }).isInstanceOf(IncorrectGameStatusException.class)
            .hasMessageContaining(GAME_HAS_ALREADY_STARTED_EXCEPTION_TEXT);
        secondState = new GameSessionState(gameSession);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForStartGameWithNullWord")
    public void startGameWithNullWord_ShouldThrowNullPointerException(String input) {
        final GameSession gameSession = new GameSession();
        final GameSessionState firstState;
        final GameSessionState secondState;

        firstState = new GameSessionState(gameSession);
        assertThatThrownBy(() -> {
            gameSession.startGame(input);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_CHOSEN_WORD_EXCEPTION_TEXT);
        secondState = new GameSessionState(gameSession);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForStartGameWithIncorrectWordLength")
    public void startGameWithIncorrectWordLength_ShouldThrowIncorrectWordLengthException(String input) {
        final GameSession gameSession = new GameSession();
        final GameSessionState firstState;
        final GameSessionState secondState;

        firstState = new GameSessionState(gameSession);
        assertThatThrownBy(() -> {
            gameSession.startGame(input);
        }).isInstanceOf(IncorrectWordLengthException.class)
            .hasMessageContaining(INCORRECT_WORD_LENGTH_EXCEPTION_TEXT);
        secondState = new GameSessionState(gameSession);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForStartGameWithIncorrectSymbolsInWord")
    public void startGameWithIncorrectSymbolsInWord_ShouldThrowIncorrectLetterException(String input) {
        final GameSession gameSession = new GameSession();
        final GameSessionState firstState;
        final GameSessionState secondState;

        firstState = new GameSessionState(gameSession);
        assertThatThrownBy(() -> {
            gameSession.startGame(input);
        }).isInstanceOf(IncorrectLetterException.class)
            .hasMessageContaining(INCORRECT_LETTERS_IN_WORDS_EXCEPTION_TEXT);
        secondState = new GameSessionState(gameSession);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGuessLetter")
    public void guessLetter_ShouldReturnTrue(String word, Set<Character> uniqueLetters) {
        final GameSession gameSession = new GameSession();
        guessProcessing(gameSession, word, uniqueLetters);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForNotGuessLetter")
    public void guessLetter_ShouldReturnFalse(String word, Set<Character> uniqueLetters) {
        final GameSession gameSession = new GameSession();
        notGuessProcessing(gameSession, word, uniqueLetters);
    }

    @ParameterizedTest
    @MethodSource("getLetters")
    public void tryGuessLetterByCorrectSymbols_ShouldReturnTrue(Character letter) {
        final GameSession gameSession = new GameSession();
        final String word = String.valueOf(letter);

        final int correctInputLettersSize = 1;
        final boolean guessResult;

        final List<Character> inputLetters;
        final String guessedWord;

        gameSession.startGame(word);
        guessResult = gameSession.guessLetter(letter);
        inputLetters = gameSession.inputLetters();
        guessedWord = gameSession.getGuessedWord();

        assertTrue(guessResult);
        assertEquals(gameSession.gameStatus(), GameStatus.WIN);
        assertEquals(GameSession.MAX_ATTEMPTS(), gameSession.remainingAttempts());
        assertEquals(gameSession.getHangmanStage(), HANGMAN_STAGES.getFirst());
        assertEquals(word.toUpperCase(), guessedWord);
        assertNotNull(inputLetters);
        assertEquals(inputLetters.size(), correctInputLettersSize);
        assertTrue(inputLetters.contains(Character.toUpperCase(letter)));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGuessLetter")
    public void guessLetterAfterWinning_ShouldThrowIncorrectGameStatusException(String word, Set<Character> uniqueLetters) {
        final GameSession gameSession = new GameSession();
        final GameSessionState firstState;
        final GameSessionState secondState;

        guessProcessing(gameSession, word, uniqueLetters);
        firstState = new GameSessionState(gameSession);
        guessLetterAfterEndingGame(gameSession);
        secondState = new GameSessionState(gameSession);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForNotGuessLetter")
    public void guessLetterAfterLosing_ShouldThrowIncorrectGameStatusException(String word, Set<Character> uniqueLetters) {
        final GameSession gameSession = new GameSession();
        final GameSessionState firstState;
        final GameSessionState secondState;

        notGuessProcessing(gameSession, word, uniqueLetters);
        firstState = new GameSessionState(gameSession);
        guessLetterAfterEndingGame(gameSession);
        secondState = new GameSessionState(gameSession);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGuessLetterByIncorrectSymbol")
    public void guessLetterByIncorrectSymbol_ShouldThrowIncorrectLetterException(Character letter) {
        final List<String> words = getWords();

        for (String word : words) {
            final GameSession gameSession = new GameSession();
            final GameSessionState firstState;
            final GameSessionState secondState;

            gameSession.startGame(word);

            firstState = new GameSessionState(gameSession);
            assertThatThrownBy(() -> {
                gameSession.guessLetter(letter);
            }).isInstanceOf(IncorrectLetterException.class)
                .hasMessageContaining(INCORRECT_LETTER_EXCEPTION_TEXT);
            secondState = new GameSessionState(gameSession);

            assertEquals(firstState, secondState);
        }
    }

    @ParameterizedTest
    @MethodSource("getLetters")
    public void guessOneLetterSeveralTimes_ShouldThrowDuplicateLetterException(Character letter) {
        final List<String> words = getWords();

        for (String word : words) {
            final GameSession gameSession = new GameSession();
            final GameSessionState firstState;
            final GameSessionState secondState;

            gameSession.startGame(word);
            gameSession.guessLetter(letter);

            firstState = new GameSessionState(gameSession);
            assertThatThrownBy(() -> {
                gameSession.guessLetter(letter);
            }).isInstanceOf(DuplicateLetterException.class)
                .hasMessageContaining(LETTER_HAS_ALREADY_INPUTTED_EXCEPTION_TEXT);
            secondState = new GameSessionState(gameSession);

            assertEquals(firstState, secondState);
        }
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGuessLetter")
    public void startGameAfterWinning_ShouldThrowIncorrectGameStatusException(String word, Set<Character> uniqueLetters) {
        final GameSession gameSession = new GameSession();
        final GameSessionState firstState;
        final GameSessionState secondState;

        guessProcessing(gameSession, word, uniqueLetters);
        firstState = new GameSessionState(gameSession);
        startGameAfterEndingGame(gameSession, word);
        secondState = new GameSessionState(gameSession);

        assertEquals(firstState, secondState);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForNotGuessLetter")
    public void startGameAfterLosing_ShouldThrowIncorrectGameStatusException(String word, Set<Character> uniqueLetters) {
        final GameSession gameSession = new GameSession();
        final GameSessionState firstState;
        final GameSessionState secondState;

        notGuessProcessing(gameSession, word, uniqueLetters);
        firstState = new GameSessionState(gameSession);
        startGameAfterEndingGame(gameSession, word);
        secondState = new GameSessionState(gameSession);

        assertEquals(firstState, secondState);
    }

    private void guessProcessing(GameSession gameSession, String word, Set<Character> uniqueLetters) {
        final List<Character> guessedLetters = new ArrayList<>();

        gameSession.startGame(word);

        for (Character letter : uniqueLetters) {
            final boolean result = gameSession.guessLetter(letter);
            final List<Character> inputLetters = gameSession.inputLetters();
            final String guessedWord = gameSession.getGuessedWord();

            guessedLetters.add(Character.toUpperCase(letter));

            assertTrue(result);
            assertEquals(word.length(), guessedWord.length());
            assertEquals(inputLetters, guessedLetters);
            assertEquals(gameSession.getHangmanStage(), HANGMAN_STAGES.getFirst());
            assertEquals(GameSession.MAX_ATTEMPTS(), gameSession.remainingAttempts());
            assertTrue((gameSession.gameStatus() == GameStatus.IN_PROCESS && !word.equals(guessedWord))
                || (gameSession.gameStatus() == GameStatus.WIN && word.equals(guessedWord)));

            for (int index = 0; index < word.length(); index++) {
                assertTrue((!inputLetters.contains(word.charAt(index)) && guessedWord.charAt(index) == '_')
                    || (word.charAt(index) == guessedWord.charAt(index)));
            }
        }
    }

    private void notGuessProcessing(GameSession gameSession, String word, Set<Character> uniqueLetters) {
        final List<Character> guessedLetters = new ArrayList<>();
        int remainingAttemptsCount = GameSession.MAX_ATTEMPTS();

        gameSession.startGame(word);

        final String guessedWord = gameSession.getGuessedWord();

        for (Character letter : uniqueLetters) {
            final boolean result = gameSession.guessLetter(letter);
            final List<Character> inputLetters = gameSession.inputLetters();

            guessedLetters.add(Character.toUpperCase(letter));
            remainingAttemptsCount--;

            assertEquals(guessedWord, gameSession.getGuessedWord());
            assertFalse(result);
            assertEquals(inputLetters, guessedLetters);
            assertEquals(word.length(), guessedWord.length());
            assertEquals(gameSession.getHangmanStage(), HANGMAN_STAGES.get(GameSession.MAX_ATTEMPTS() - remainingAttemptsCount));
            assertEquals(remainingAttemptsCount, gameSession.remainingAttempts());
            assertTrue((gameSession.gameStatus() == GameStatus.IN_PROCESS && remainingAttemptsCount != 0)
                || (gameSession.gameStatus() == GameStatus.LOSE && remainingAttemptsCount == 0));
        }
    }

    private void guessLetterAfterEndingGame(GameSession gameSession) {
        final char defaultLetter = 'а';

        assertThatThrownBy(() -> {
            gameSession.guessLetter(defaultLetter);
        }).isInstanceOf(IncorrectGameStatusException.class)
            .hasMessageContaining(GAME_HAS_ALREADY_ENDED_EXCEPTION_TEXT);
    }

    private void startGameAfterEndingGame(GameSession gameSession, String word) {
        assertThatThrownBy(() -> {
            gameSession.startGame(word);
        }).isInstanceOf(IncorrectGameStatusException.class)
            .hasMessageContaining(GAME_HAS_ALREADY_ENDED_EXCEPTION_TEXT);
    }

    private static List<String> getArgumentsForStartGame() {
        final List<String> testValues = new ArrayList<>(getWords());
        final String letter = "а";

        testValues.addAll(
            testValues
                .stream()
                .map(String::toLowerCase)
                .toList()
        );

        for (int count = 1; count <= GameSession.CHOSEN_WORD_MAX_LENGTH(); count++) {
            testValues.add(letter.repeat(count));
        }

        return testValues;
    }

    private static List<String> getArgumentsForStartGameWithNullWord() {
        return Arrays.asList(null, "", " ", "   ");
    }

    private static List<String> getArgumentsForStartGameWithIncorrectWordLength() {
        final int wordsCount = 10;
        final List<String> testValues = new ArrayList<>(wordsCount);
        final String letter = "а";

        for (int index = 1; index <= wordsCount; index++) {
            testValues.add(letter.repeat(GameSession.CHOSEN_WORD_MAX_LENGTH() + index));
        }

        return testValues;
    }

    private static List<String> getArgumentsForStartGameWithIncorrectSymbolsInWord() {
        final List<String> testValues = new ArrayList<>();
        final int maxRepeatCount = 5;

        final int minNonLetterNumber = 33;
        final int maxNonLetterNumber = 127;

        for (int repeatCount = 1; repeatCount <= maxRepeatCount; repeatCount++) {
            for (int i = minNonLetterNumber; i <= maxNonLetterNumber; i++) {
                String symbol = String.valueOf((char) i);
                testValues.add(symbol.repeat(repeatCount));
            }
        }

        return testValues;
    }


    private static List<Object[]> getArgumentsForGuessLetter() {
        final List<Object[]> testValues = new ArrayList<>();
        final List<String> words = getWords();

        for (String word : words) {
            final Set<Character> uniqueUpperCaseLetters = word.chars()
                .mapToObj(c -> (char) c)
                .map(Character::toUpperCase)
                .collect(Collectors.toSet());

            final Set<Character> uniqueLowerCaseLetters = uniqueUpperCaseLetters
                .stream()
                .map(Character::toLowerCase)
                .collect(Collectors.toSet());

            testValues.add(
                new Object[]{word, uniqueUpperCaseLetters}
            );

            testValues.add(
                new Object[]{word, uniqueLowerCaseLetters}
            );
        }

        return testValues;
    }

    private static List<Object[]> getArgumentsForNotGuessLetter() {
        final List<Object[]> testValues = new ArrayList<>();
        final List<String> words = getWords();

        final int minLetterNumber = 1040;
        final int maxLetterNumber = 1103;

        for (String word : words) {
            final Set<Character> uniqueUpperCaseLetters = IntStream.rangeClosed(minLetterNumber, maxLetterNumber)
                .mapToObj(i -> (char) i)
                .filter(symbol -> !word.contains(String.valueOf(symbol)))
                .map(Character::toUpperCase)
                .limit(GameSession.MAX_ATTEMPTS())
                .collect(Collectors.toSet());

            final Set<Character> uniqueLowerCaseLetters = uniqueUpperCaseLetters
                .stream()
                .map(Character::toLowerCase)
                .collect(Collectors.toSet());

            testValues.add(
                new Object[]{word, uniqueUpperCaseLetters}
            );

            testValues.add(
                new Object[]{word, uniqueLowerCaseLetters}
            );
        }

        return testValues;
    }

    private static List<String> getWords() {
        return HINTERS.keySet().stream().toList();
    }
}
