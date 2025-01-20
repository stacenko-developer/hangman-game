package backend.academy.hangman;

import backend.academy.hangman.enums.GameLevel;
import backend.academy.hangman.enums.WordsCategory;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import backend.academy.hangman.words.WordsGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordsGeneratorTest {

    @ParameterizedTest
    @MethodSource("getArgumentsForGetRandomWordByCategoryAndLevel")
    public void getRandomWordByCategoryAndLevel_ShouldReturnWord(WordsCategory wordsCategory, GameLevel gameLevel, List<String> expectedWords) {
        final WordsGenerator wordsGenerator = new WordsGenerator();
        final String generatedWord;

        wordsGenerator.setWordsCategory(wordsCategory);
        wordsGenerator.setGameLevel(gameLevel);
        generatedWord = wordsGenerator.getRandomWord();

        assertThat(expectedWords).contains(generatedWord);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGetRandomWordByNullCategoryAndLevel")
    public void getRandomWordByNullCategoryAndLevel_ShouldReturnWord(GameLevel gameLevel, List<String> expectedWords) {
        final WordsGenerator wordsGenerator = new WordsGenerator();
        final String generatedWord;

        wordsGenerator.setGameLevel(gameLevel);
        generatedWord = wordsGenerator.getRandomWord();

        assertThat(expectedWords).contains(generatedWord);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGetRandomWordByCategoryAndNullLevel")
    public void getRandomWordByCategoryAndNullLevel_ShouldReturnWord(WordsCategory wordsCategory, List<String> expectedWords) {
        final WordsGenerator wordsGenerator = new WordsGenerator();
        final String generatedWord;

        wordsGenerator.setWordsCategory(wordsCategory);
        generatedWord = wordsGenerator.getRandomWord();

        assertThat(expectedWords).contains(generatedWord);
    }

    @Test
    public void getRandomWordByNullCategoryAndNullLevel_ShouldReturnWord() {
        final WordsGenerator wordsGenerator = new WordsGenerator();
        final List<String> expectedWords = Stream
            .of(ANIMALS_FOR_EASY_LEVEL, ANIMALS_FOR_MEDIUM_LEVEL, ANIMALS_FOR_HARD_LEVEL,
                FRUITS_FOR_EASY_LEVEL, FRUITS_FOR_MEDIUM_LEVEL, FRUITS_FOR_HARD_LEVEL,
                REST_FOR_EASY_LEVEL, REST_FOR_MEDIUM_LEVEL, REST_FOR_HARD_LEVEL,
                FOOD_FOR_EASY_LEVEL, FOOD_FOR_MEDIUM_LEVEL, FOOD_FOR_HARD_LEVEL,
                ELECTRONICS_FOR_EASY_LEVEL, ELECTRONICS_FOR_MEDIUM_LEVEL, ELECTRONICS_FOR_HARD_LEVEL)
            .flatMap(List::stream)
            .collect(Collectors.toList());
        final String generatedWord;

        generatedWord = wordsGenerator.getRandomWord();

        assertThat(expectedWords).contains(generatedWord);
    }

    @Test
    public void setNullCategoryToWordsGenerator_ShouldThrowNullPointerException() {
        final WordsGenerator wordsGenerator = new WordsGenerator();

        assertThatThrownBy(() -> {
           wordsGenerator.setWordsCategory(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_CATEGORY_EXCEPTION_TEXT);
    }

    @Test
    public void setNullLevelToWordsGenerator_ShouldThrowNullPointerException() {
        final WordsGenerator wordsGenerator = new WordsGenerator();

        assertThatThrownBy(() -> {
            wordsGenerator.setGameLevel(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_LEVEL_EXCEPTION_TEXT);
    }

    static List<Object[]> getArgumentsForGetRandomWordByNullCategoryAndLevel() {
        return List.of(
            new Object[] {GameLevel.EASY, Stream.of(ANIMALS_FOR_EASY_LEVEL, FRUITS_FOR_EASY_LEVEL,
                    REST_FOR_EASY_LEVEL, FOOD_FOR_EASY_LEVEL, ELECTRONICS_FOR_EASY_LEVEL)
                .flatMap(List::stream)
                .collect(Collectors.toList())
            },
            new Object[] {GameLevel.MEDIUM, Stream.of(ANIMALS_FOR_MEDIUM_LEVEL, FRUITS_FOR_MEDIUM_LEVEL,
                    REST_FOR_MEDIUM_LEVEL, FOOD_FOR_MEDIUM_LEVEL, ELECTRONICS_FOR_MEDIUM_LEVEL)
                .flatMap(List::stream)
                .collect(Collectors.toList())
            },
            new Object[] {GameLevel.HARD, Stream.of(ANIMALS_FOR_HARD_LEVEL, FRUITS_FOR_HARD_LEVEL,
                    REST_FOR_HARD_LEVEL, FOOD_FOR_HARD_LEVEL, ELECTRONICS_FOR_HARD_LEVEL)
                .flatMap(List::stream)
                .collect(Collectors.toList())
            }
        );
    }

    static List<Object[]> getArgumentsForGetRandomWordByCategoryAndNullLevel() {
        return List.of(
            new Object[] {WordsCategory.ANIMALS, Stream.of(ANIMALS_FOR_EASY_LEVEL, ANIMALS_FOR_MEDIUM_LEVEL,
                    ANIMALS_FOR_HARD_LEVEL)
                .flatMap(List::stream)
                .collect(Collectors.toList())
            },
            new Object[] {WordsCategory.FRUITS, Stream.of(FRUITS_FOR_EASY_LEVEL, FRUITS_FOR_MEDIUM_LEVEL,
                    FRUITS_FOR_HARD_LEVEL)
                .flatMap(List::stream)
                .collect(Collectors.toList())
            },
            new Object[] {WordsCategory.REST, Stream.of(REST_FOR_EASY_LEVEL, REST_FOR_MEDIUM_LEVEL, REST_FOR_HARD_LEVEL)
                .flatMap(List::stream)
                .collect(Collectors.toList())
            },
            new Object[] {WordsCategory.FOOD, Stream.of(FOOD_FOR_EASY_LEVEL, FOOD_FOR_MEDIUM_LEVEL, FOOD_FOR_HARD_LEVEL)
                .flatMap(List::stream)
                .collect(Collectors.toList())
            },
            new Object[] {WordsCategory.ELECTRONICS, Stream.of(ELECTRONICS_FOR_EASY_LEVEL, ELECTRONICS_FOR_MEDIUM_LEVEL,
                    ELECTRONICS_FOR_HARD_LEVEL)
                .flatMap(List::stream)
                .collect(Collectors.toList())
            }
        );
    }

    static List<Object[]> getArgumentsForGetRandomWordByCategoryAndLevel() {
        return List.of(
            new Object[] {WordsCategory.ANIMALS, GameLevel.EASY, ANIMALS_FOR_EASY_LEVEL},
            new Object[] {WordsCategory.ANIMALS, GameLevel.MEDIUM, ANIMALS_FOR_MEDIUM_LEVEL},
            new Object[] {WordsCategory.ANIMALS, GameLevel.HARD, ANIMALS_FOR_HARD_LEVEL},
            new Object[] {WordsCategory.FRUITS, GameLevel.EASY, FRUITS_FOR_EASY_LEVEL},
            new Object[] {WordsCategory.FRUITS, GameLevel.MEDIUM, FRUITS_FOR_MEDIUM_LEVEL},
            new Object[] {WordsCategory.FRUITS, GameLevel.HARD, FRUITS_FOR_HARD_LEVEL},
            new Object[] {WordsCategory.REST, GameLevel.EASY, REST_FOR_EASY_LEVEL},
            new Object[] {WordsCategory.REST, GameLevel.MEDIUM, REST_FOR_MEDIUM_LEVEL},
            new Object[] {WordsCategory.REST, GameLevel.HARD, REST_FOR_HARD_LEVEL},
            new Object[] {WordsCategory.FOOD, GameLevel.EASY, FOOD_FOR_EASY_LEVEL},
            new Object[] {WordsCategory.FOOD, GameLevel.MEDIUM, FOOD_FOR_MEDIUM_LEVEL},
            new Object[] {WordsCategory.FOOD, GameLevel.HARD, FOOD_FOR_HARD_LEVEL},
            new Object[] {WordsCategory.ELECTRONICS, GameLevel.EASY, ELECTRONICS_FOR_EASY_LEVEL},
            new Object[] {WordsCategory.ELECTRONICS, GameLevel.MEDIUM, ELECTRONICS_FOR_MEDIUM_LEVEL},
            new Object[] {WordsCategory.ELECTRONICS, GameLevel.HARD, ELECTRONICS_FOR_HARD_LEVEL}
        );
    }

}
