package backend.academy.hangman.dto;

import backend.academy.hangman.game.GameSession;
import backend.academy.hangman.enums.GameStatus;
import java.util.List;
import java.util.Objects;

public class GameSessionState {

    private final GameStatus gameStatus;
    private final int remainingAttempts;

    private final String hangmanStage;
    private final String guessedWord;
    private final List<Character> inputLetters;

    public GameSessionState(GameSession gameSession) {
        this.gameStatus = gameSession.gameStatus();
        this.remainingAttempts = gameSession.remainingAttempts();
        this.hangmanStage = gameSession.getHangmanStage();
        this.guessedWord = gameSession.getGuessedWord();
        this.inputLetters = gameSession.inputLetters();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final GameSessionState that = (GameSessionState) o;

        return remainingAttempts == that.remainingAttempts
            && gameStatus == that.gameStatus
            && Objects.equals(hangmanStage, that.hangmanStage)
            && Objects.equals(guessedWord, that.guessedWord)
            && Objects.equals(inputLetters, that.inputLetters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameStatus, remainingAttempts, hangmanStage, guessedWord, inputLetters);
    }
}
