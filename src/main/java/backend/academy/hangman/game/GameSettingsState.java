package backend.academy.hangman.game;

import backend.academy.hangman.enums.GameLevel;
import backend.academy.hangman.enums.WordsCategory;
import java.util.Objects;

public class GameSettingsState {

    private final WordsCategory wordsCategory;
    private final GameLevel gameLevel;

    public GameSettingsState(WordsCategory wordsCategory, GameLevel gameLevel) {
        this.wordsCategory = wordsCategory;
        this.gameLevel = gameLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final GameSettingsState that = (GameSettingsState) o;

        return Objects.equals(wordsCategory, that.wordsCategory)
            && Objects.equals(gameLevel, that.gameLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordsCategory, gameLevel);
    }
}
