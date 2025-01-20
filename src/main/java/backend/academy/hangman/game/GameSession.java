package backend.academy.hangman.game;

import backend.academy.hangman.constants.ConstValues;
import backend.academy.hangman.enums.GameStatus;
import backend.academy.hangman.exception.DuplicateLetterException;
import backend.academy.hangman.exception.IncorrectGameStatusException;
import backend.academy.hangman.exception.IncorrectLetterException;
import backend.academy.hangman.exception.IncorrectWordLengthException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import static backend.academy.hangman.constants.ExceptionTextValues.GAME_HAS_ALREADY_ENDED_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.GAME_HAS_ALREADY_STARTED_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.INCORRECT_LETTERS_IN_WORDS_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.INCORRECT_LETTER_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.INCORRECT_WORD_LENGTH_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.LETTER_HAS_ALREADY_INPUTTED_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.NULL_CHOSEN_WORD_EXCEPTION_TEXT;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class GameSession {

    private static final String LETTER_REGEX = "^[А-Яа-яёЁ]$";
    private static final String LETTERS_ONLY_REGEX = "^[А-Яа-яёЁ]+$";

    private static final List<String> HANGMAN_STAGES = new ArrayList<>(ConstValues.HANGMAN_STAGES);
    @Getter
    private static final int MAX_ATTEMPTS = HANGMAN_STAGES.size() - 1;
    @Getter
    private static final int CHOSEN_WORD_MAX_LENGTH = 20;

    @Getter
    private final List<Character> inputLetters = new ArrayList<>();

    private char[] guessedWord = new char[0];
    private String chosenWord;

    @Getter
    private GameStatus gameStatus;
    @Getter
    private int remainingAttempts = MAX_ATTEMPTS;

    public GameSession() {
        gameStatus = GameStatus.BEGIN_WAITING;
    }

    public void startGame(String chosenWord) {
        validateGameStatusBeforeStart();
        validateChosenWord(chosenWord);

        this.chosenWord = chosenWord.toUpperCase();
        this.gameStatus = GameStatus.IN_PROCESS;
        guessedWord = new char[chosenWord.length()];
        char symbol = '_';
        Arrays.fill(guessedWord, symbol);
    }

    public boolean guessLetter(char letter) {
        validateGameStatusBeforeGuess();
        validateLetter(letter);

        final char letterInUpperCase = Character.toUpperCase(letter);

        validateLetterContaining(letterInUpperCase);

        inputLetters.add(letterInUpperCase);

        if (chosenWord.contains(String.valueOf(letterInUpperCase))) {
            updateGuessedWord(letterInUpperCase);

            if (String.valueOf(guessedWord).equals(chosenWord)) {
                gameStatus = GameStatus.WIN;
            }

            return true;
        } else {
            remainingAttempts--;

            if (remainingAttempts == 0) {
                gameStatus = GameStatus.LOSE;
            }
        }

        return false;
    }

    public String getGuessedWord() {
        return String.valueOf(guessedWord);
    }

    public String getHangmanStage() {
        return HANGMAN_STAGES.get(MAX_ATTEMPTS - remainingAttempts);
    }

    private void validateChosenWord(String chosenWord) {
        if (isBlank(chosenWord)) {
            throw new NullPointerException(NULL_CHOSEN_WORD_EXCEPTION_TEXT);
        }
        if (!chosenWord.matches(LETTERS_ONLY_REGEX)) {
            throw new IncorrectLetterException(INCORRECT_LETTERS_IN_WORDS_EXCEPTION_TEXT);
        }
        if (chosenWord.length() > CHOSEN_WORD_MAX_LENGTH) {
            throw new IncorrectWordLengthException(INCORRECT_WORD_LENGTH_EXCEPTION_TEXT);
        }
    }

    private void validateGameStatusBeforeStart() {
        if (gameStatus == GameStatus.IN_PROCESS) {
            throw new IncorrectGameStatusException(GAME_HAS_ALREADY_STARTED_EXCEPTION_TEXT);
        }
        if (gameStatus == GameStatus.WIN || gameStatus == GameStatus.LOSE) {
            throw new IncorrectGameStatusException(GAME_HAS_ALREADY_ENDED_EXCEPTION_TEXT);
        }
    }

    private void validateGameStatusBeforeGuess() {
        if (gameStatus == GameStatus.WIN || gameStatus == GameStatus.LOSE) {
            throw new IncorrectGameStatusException(GAME_HAS_ALREADY_ENDED_EXCEPTION_TEXT);
        }
    }

    private void validateLetter(char letter) {
        if (!String.valueOf(letter).matches(LETTER_REGEX)) {
            throw new IncorrectLetterException(INCORRECT_LETTER_EXCEPTION_TEXT);
        }
    }

    private void validateLetterContaining(char letter) {
        if (inputLetters.contains(letter)) {
            throw new DuplicateLetterException(LETTER_HAS_ALREADY_INPUTTED_EXCEPTION_TEXT);
        }
    }

    private void updateGuessedWord(char letter) {
        for (int index = 0; index < chosenWord.length(); index++) {
            if (chosenWord.charAt(index) == letter) {
                guessedWord[index] = letter;
            }
        }
    }
}
