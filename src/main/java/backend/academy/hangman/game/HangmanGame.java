package backend.academy.hangman.game;

import backend.academy.hangman.consoleInput.ConsoleReader;
import backend.academy.hangman.consoleInput.InputScanner;
import backend.academy.hangman.enums.GameLevel;
import backend.academy.hangman.enums.GameStatus;
import backend.academy.hangman.enums.WordsCategory;
import backend.academy.hangman.exception.DuplicateLetterException;
import backend.academy.hangman.exception.IncorrectGameStatusException;
import backend.academy.hangman.exception.IncorrectInputException;
import backend.academy.hangman.words.HintProvider;
import backend.academy.hangman.words.WordsGenerator;
import java.io.PrintStream;
import lombok.Getter;
import static backend.academy.hangman.constants.ExceptionTextValues.UNKNOWN_ERROR_EXCEPTION_TEXT;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class HangmanGame {

    private final GameLevel[] levels = GameLevel.values();
    private final WordsCategory[] categories = WordsCategory.values();
    private final ConsoleReader consoleReader;
    private final GamePrinter gamePrinter;

    private WordsCategory wordsCategory;
    private GameLevel gameLevel;

    @Getter
    private GameSession gameSession = new GameSession();
    private String hint;
    private String word;

    private int menu = -1;

    public HangmanGame(PrintStream output) {
        this(null, output);
    }

    public HangmanGame(InputScanner inputScanner, PrintStream output) {
        this.consoleReader = inputScanner != null
            ? new ConsoleReader(inputScanner)
            : new ConsoleReader();

        this.gamePrinter = output != null
            ? new GamePrinter(output)
            : new GamePrinter(System.out);
    }

    /**
     * Запускает игру, отображает главное меню и обрабатывает выбор пользователя.
     */
    public void start() {
        final int exitOption = 0;
        final int startGameOption = 3;

        gameSession = new GameSession();
        hint = null;

        while (menu != exitOption && menu != startGameOption) {
            gamePrinter.printMainMenu(wordsCategory, gameLevel);

            try {
                readMenuOption();
            } catch (IncorrectInputException ex) {
                gamePrinter.printErrorText(ex.getMessage());
            } catch (RuntimeException ex) {
                gamePrinter.printErrorText(UNKNOWN_ERROR_EXCEPTION_TEXT);
            }
        }
    }

    /**
     * Читает и обрабатывает выбор пользователя в главном меню.
     *
     * @throws IncorrectInputException если ввод пользователя некорректен.
     */
    public void readMenuOption() {
        final int chooseLevelOption = 1;
        final int chooseCategoryOption = 2;
        final int startGameOption = 3;
        final int minMenuOptionNumber = 0;
        final int maxMenuOptionNumber = 3;

        menu = consoleReader.readPositiveInteger(minMenuOptionNumber, maxMenuOptionNumber);

        switch (menu) {
            case chooseLevelOption:
                chooseCategory();
                break;
            case chooseCategoryOption:
                chooseLevel();
                break;
            case startGameOption:
                startGame();
                break;
            default:
                break;
        }
    }

    /**
     * Обрабатывает попытку игрока угадать букву в слове.
     *
     * @throws IncorrectInputException если ввод игрока некорректен.
     * @throws DuplicateLetterException если игрок уже пытался угадать эту букву.
     */
    public void makeGuessAttempt() {
        if (isBlank(word)) {
            beginGameSession();
        }

        if (gameSession.gameStatus() != GameStatus.IN_PROCESS) {
            return;
        }

        gamePrinter.printGameStatistics(gameSession);
        gamePrinter.printHint(hint);
        gamePrinter.printChooseLetter();

        final char letter;

        if (isBlank(hint)) {
            final String input = consoleReader.readLetterOrEnter();
            if (input.isEmpty()) {
                hint = HintProvider.getHint(word);
                gamePrinter.printHint(hint);
                gamePrinter.printChooseLetter();
                letter = Character.toUpperCase(consoleReader.readLetter());
            } else {
                letter = input.charAt(0);
            }
        } else {
            letter = Character.toUpperCase(consoleReader.readLetter());
        }

        final boolean isGuessed = gameSession.guessLetter(letter);

        gamePrinter.printGuessResult(isGuessed, letter);
    }

    /**
     * Возвращает текущее состояние настроек игры.
     *
     * @return объект GameSettingsState, содержащий текущие категорию слов и уровень игры.
     */
    public GameSettingsState getGameSettingsState() {
        return new GameSettingsState(wordsCategory, gameLevel);
    }

    /**
     * Запускает игровой процесс. В этом методе происходит игра до окончания.
     */
    private void startGame() {
        beginGameSession();

        while (gameSession.gameStatus() == GameStatus.IN_PROCESS) {
            try {
                makeGuessAttempt();
            } catch (IncorrectInputException | DuplicateLetterException | IncorrectGameStatusException ex) {
                gamePrinter.printErrorText(ex.getMessage());
            } catch (RuntimeException ex) {
                gamePrinter.printErrorText(UNKNOWN_ERROR_EXCEPTION_TEXT);
            }
        }

        gamePrinter.printGameResult(gameSession, word);
    }

    /**
     * Инициализирует новую игровую сессию и генерирует слово для отгадывания.
     */
    private void beginGameSession() {
        final WordsGenerator wordsGenerator = new WordsGenerator();

        if (gameLevel != null) {
            wordsGenerator.setGameLevel(gameLevel);
        }

        if (wordsCategory != null) {
            wordsGenerator.setWordsCategory(wordsCategory);
        }

        word = wordsGenerator.getRandomWord();
        gameSession.startGame(word);
        gamePrinter.printStartGame(wordsGenerator.wordsCategory(), wordsGenerator.gameLevel());
    }

    /**
     * Позволяет пользователю выбрать уровень игры.
     *
     * @throws IncorrectInputException если ввод игрока некорректен.
     */
    private void chooseLevel() {
        final int minValue = 0;
        gamePrinter.printChooseGameLevel();
        gameLevel = levels[consoleReader.readPositiveInteger(minValue, levels.length - 1)];
    }

    /**
     * Позволяет пользователю выбрать категорию слов для игры.
     *
     * @throws IncorrectInputException если ввод игрока некорректен.
     */
    private void chooseCategory() {
        final int minValue = 0;
        gamePrinter.printChooseWordsCategory();
        wordsCategory = categories[consoleReader.readPositiveInteger(minValue, categories.length - 1)];
    }
}
