package backend.academy.hangman.game;

import backend.academy.hangman.enums.GameLevel;
import backend.academy.hangman.enums.GameStatus;
import backend.academy.hangman.enums.WordsCategory;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class GamePrinter {

    private static final String NOT_CHOSEN_TEXT = "не выбрана";
    private final PrintStream output;

    public GamePrinter(PrintStream output) {
        this.output = output == null
            ? System.out
            : output;
    }

    public void printMainMenu(WordsCategory wordsCategory, GameLevel gameLevel) {
        final String format = "Игра 'Виселица'%nКатегория: %s%nУровень сложности: %s%n";
        final List<String> menuOptions = List.of(
            "Выход",
            "Выбрать категорию",
            "Выбрать уровень сложности",
            "Начать игру"
        );

        output.format(format,
            wordsCategory == null ? NOT_CHOSEN_TEXT : wordsCategory.value(),
            gameLevel == null ? NOT_CHOSEN_TEXT : gameLevel.value()
        );

        printMenuOptions(menuOptions);
        printInputYourChoice();
    }

    public void printErrorText(String text) {
        final String errorTextFormat = "Обнаружена ошибка: %s%n";

        output.format(errorTextFormat, text);
    }

    public void printStartGame(WordsCategory wordsCategory, GameLevel gameLevel) {
        final String format = "Игра началась! Категория: %s%nУровень сложности: %s%nПопробуйте угадать слово.%n";

        output.format(format,
            wordsCategory == null ? NOT_CHOSEN_TEXT : wordsCategory.value(),
            gameLevel == null ? NOT_CHOSEN_TEXT : gameLevel.value()
        );
    }

    public void printGuessResult(boolean isGuessed, char letter) {
        final String guessedLetterTextFormat = "Вы отгадали букву %s%n";

        if (isGuessed) {
            output.format(guessedLetterTextFormat, letter);
        } else {
            output.println("Вы не отгадали букву");
        }
    }

    public void printGameResult(GameSession gameSession, String word) {
        final String winGameTextFormat = "Поздравляем! Вы угадали слово: %s%n";
        final String loseGameTextFormat = "%s%nВы проиграли! Загаданное слово: %s%n";

        if (gameSession != null && isNotBlank(word)) {
            if (gameSession.gameStatus() == GameStatus.WIN) {
                output.format(winGameTextFormat, word);
            } else {
                output.format(loseGameTextFormat, gameSession.getHangmanStage(), word);
            }
        }
    }

    public void printChooseGameLevel() {
        final List<String> gameLevels = Arrays.stream(GameLevel.values())
            .map(GameLevel::value)
            .toList();

        output.println("Выбор уровня сложности:");
        printMenuOptions(gameLevels);
        printInputYourChoice();
    }

    public void printChooseWordsCategory() {
        final List<String> wordsCategories = Arrays.stream(WordsCategory.values())
            .map(WordsCategory::value)
            .toList();

        output.println("Выбор категории:");
        printMenuOptions(wordsCategories);
        printInputYourChoice();
    }

    public void printChooseLetter() {
        output.println("Введите букву: ");
    }

    public void printGameStatistics(GameSession gameSession) {
        if (gameSession == null) {
            return;
        }

        final String format = "Слово:%s%n%s%nКоличество попыток: %d%nВведенные буквы:%s%n";

        output.format(format, gameSession.getGuessedWord(),
            gameSession.getHangmanStage(), gameSession.remainingAttempts(), gameSession.inputLetters()
        );

    }

    public void printHint(String hint) {
        if (isNotBlank(hint)) {
            output.format("Подсказка: %s%n", hint);
        } else {
            output.println("Для включения подсказки нажмите Enter");
        }
    }

    private void printInputYourChoice() {
        output.println("Введите ваш выбор: ");
    }

    private void printMenuOptions(List<?> menuOptions) {
        if (menuOptions == null) {
            return;
        }

        final String format = "%d - %s%n";

        for (int index = 0; index < menuOptions.size(); index++) {
            output.format(format, index, menuOptions.get(index));
        }
    }
}
