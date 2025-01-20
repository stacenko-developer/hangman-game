package backend.academy.hangman.enums;

import lombok.Getter;

@Getter
public enum WordsCategory {

    ANIMALS("Животные"),
    REST("Отдых"),
    FRUITS("Фрукты"),
    FOOD("Еда"),
    ELECTRONICS("Электроника");

    private final String value;

    WordsCategory(String value) {
        this.value = value;
    }
}
