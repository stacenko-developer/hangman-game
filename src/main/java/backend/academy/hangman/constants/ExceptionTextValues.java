package backend.academy.hangman.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionTextValues {
    public final static String NULL_CATEGORY_EXCEPTION_TEXT = "Значение категории не должно быть null";
    public final static String NULL_LEVEL_EXCEPTION_TEXT = "Значение уровня не должно быть null";
    public final static String NULL_WORD_EXCEPTION_TEXT = "Значение слова не должно быть null";
    public final static String NO_SUCH_HINTER_EXCEPTION_TEXT = "Подсказка для введенного слова не найдена";
    public final static String GAME_HAS_ALREADY_STARTED_EXCEPTION_TEXT = "Игра уже начата";
    public final static String GAME_HAS_ALREADY_ENDED_EXCEPTION_TEXT = "Игра уже окончена";
    public final static String NULL_CHOSEN_WORD_EXCEPTION_TEXT = "Значение выбранного слова не должно быть null";
    public final static String LETTER_HAS_ALREADY_INPUTTED_EXCEPTION_TEXT = "Вы уже указывали данную букву";
    public final static String INCORRECT_INPUT_EXCEPTION_TEXT = "Введено некорректное значение";
    public final static String INCORRECT_LETTER_EXCEPTION_TEXT = "Введенный символ не является буквой";
    public final static String INCORRECT_WORD_LENGTH_EXCEPTION_TEXT = "Превышена допустимая длина слова";
    public final static String INCORRECT_LETTERS_IN_WORDS_EXCEPTION_TEXT = "В слове встречен небуквенный символ";
    public final static String UNKNOWN_ERROR_EXCEPTION_TEXT = "Неизвестная ошибка";
}
