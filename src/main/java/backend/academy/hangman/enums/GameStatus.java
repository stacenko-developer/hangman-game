package backend.academy.hangman.enums;

import lombok.Getter;

@Getter
public enum GameStatus {

    BEGIN_WAITING("В ожидании начала игры"),
    WIN("Победа"),
    IN_PROCESS("В процессе"),
    LOSE("Поражение");

    private final String value;

    GameStatus(String value) {
        this.value = value;
    }
}
