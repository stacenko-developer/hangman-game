package backend.academy.hangman.enums;

import lombok.Getter;

@Getter
public enum GameLevel {

    EASY("Лёгкий"),
    MEDIUM("Средний"),
    HARD("Сложный");

    private final String value;

    GameLevel(String value) {
        this.value = value;
    }
}
