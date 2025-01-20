package backend.academy.hangman.dto;

import backend.academy.hangman.game.GameSession;
import backend.academy.hangman.game.GameSettingsState;
import backend.academy.hangman.game.HangmanGame;
import java.util.Objects;

public class HangmanGameState {

    private final GameSettingsState gameSettingsState;
    private final GameSession gameSession;

    public HangmanGameState(HangmanGame hangmanGameState) {
        this.gameSettingsState = hangmanGameState.getGameSettingsState();
        this.gameSession = hangmanGameState.gameSession();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final HangmanGameState that = (HangmanGameState) o;

        return Objects.equals(gameSettingsState, that.gameSettingsState)
            && Objects.equals(gameSession, that.gameSession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameSettingsState, gameSession);
    }
}
