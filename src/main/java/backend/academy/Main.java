package backend.academy;

import backend.academy.hangman.game.HangmanGame;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
         new HangmanGame(System.out).start();
    }
}
