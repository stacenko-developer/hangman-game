package backend.academy.hangman.words;

import backend.academy.hangman.enums.GameLevel;
import backend.academy.hangman.enums.WordsCategory;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import static backend.academy.hangman.constants.ConstValues.ANIMALS_FOR_EASY_LEVEL;
import static backend.academy.hangman.constants.ConstValues.ANIMALS_FOR_HARD_LEVEL;
import static backend.academy.hangman.constants.ConstValues.ANIMALS_FOR_MEDIUM_LEVEL;
import static backend.academy.hangman.constants.ConstValues.ELECTRONICS_FOR_EASY_LEVEL;
import static backend.academy.hangman.constants.ConstValues.ELECTRONICS_FOR_HARD_LEVEL;
import static backend.academy.hangman.constants.ConstValues.ELECTRONICS_FOR_MEDIUM_LEVEL;
import static backend.academy.hangman.constants.ConstValues.FOOD_FOR_EASY_LEVEL;
import static backend.academy.hangman.constants.ConstValues.FOOD_FOR_HARD_LEVEL;
import static backend.academy.hangman.constants.ConstValues.FOOD_FOR_MEDIUM_LEVEL;
import static backend.academy.hangman.constants.ConstValues.FRUITS_FOR_EASY_LEVEL;
import static backend.academy.hangman.constants.ConstValues.FRUITS_FOR_HARD_LEVEL;
import static backend.academy.hangman.constants.ConstValues.FRUITS_FOR_MEDIUM_LEVEL;
import static backend.academy.hangman.constants.ConstValues.REST_FOR_EASY_LEVEL;
import static backend.academy.hangman.constants.ConstValues.REST_FOR_HARD_LEVEL;
import static backend.academy.hangman.constants.ConstValues.REST_FOR_MEDIUM_LEVEL;
import static backend.academy.hangman.constants.ExceptionTextValues.NULL_CATEGORY_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.NULL_LEVEL_EXCEPTION_TEXT;

public class WordsGenerator {

    private final Map<WordsCategory, Map<GameLevel, List<String>>> words = getWords();
    private final GameLevel[] gameLevels = GameLevel.values();
    private final WordsCategory[] wordsCategories = WordsCategory.values();
    private final SecureRandom secureRandom = new SecureRandom();

    @Getter
    private GameLevel gameLevel;
    @Getter
    private WordsCategory wordsCategory;

    public String getRandomWord() {
        if (wordsCategory == null) {
            wordsCategory = wordsCategories[secureRandom.nextInt(wordsCategories.length - 1)];
        }

        if (gameLevel == null) {
            gameLevel = gameLevels[secureRandom.nextInt(gameLevels.length - 1)];
        }

        final List<String> wordsByCategory = words.get(wordsCategory).get(gameLevel);
        final int index = secureRandom.nextInt(wordsByCategory.size());

        return wordsByCategory.get(index);
    }

    public void setGameLevel(GameLevel gameLevel) {
        if (gameLevel == null) {
            throw new NullPointerException(NULL_LEVEL_EXCEPTION_TEXT);
        }

        this.gameLevel = gameLevel;
    }

    public void setWordsCategory(WordsCategory wordsCategory) {
        if (wordsCategory == null) {
            throw new NullPointerException(NULL_CATEGORY_EXCEPTION_TEXT);
        }

        this.wordsCategory = wordsCategory;
    }

    private Map<WordsCategory, Map<GameLevel, List<String>>> getWords() {
        return new HashMap<>(
            Map.of(
                WordsCategory.ANIMALS, Map.of(
                    GameLevel.EASY, ANIMALS_FOR_EASY_LEVEL,
                    GameLevel.MEDIUM, ANIMALS_FOR_MEDIUM_LEVEL,
                    GameLevel.HARD, ANIMALS_FOR_HARD_LEVEL
                ),
                WordsCategory.FRUITS, Map.of(
                    GameLevel.EASY, FRUITS_FOR_EASY_LEVEL,
                    GameLevel.MEDIUM, FRUITS_FOR_MEDIUM_LEVEL,
                    GameLevel.HARD, FRUITS_FOR_HARD_LEVEL
                ),
                WordsCategory.REST, Map.of(
                    GameLevel.EASY, REST_FOR_EASY_LEVEL,
                    GameLevel.MEDIUM, REST_FOR_MEDIUM_LEVEL,
                    GameLevel.HARD, REST_FOR_HARD_LEVEL
                ),
                WordsCategory.FOOD, Map.of(
                    GameLevel.EASY, FOOD_FOR_EASY_LEVEL,
                    GameLevel.MEDIUM, FOOD_FOR_MEDIUM_LEVEL,
                    GameLevel.HARD, FOOD_FOR_HARD_LEVEL
                ),
                WordsCategory.ELECTRONICS, Map.of(
                    GameLevel.EASY, ELECTRONICS_FOR_EASY_LEVEL,
                    GameLevel.MEDIUM, ELECTRONICS_FOR_MEDIUM_LEVEL,
                    GameLevel.HARD, ELECTRONICS_FOR_HARD_LEVEL
                )
            )
        );
    }
}
