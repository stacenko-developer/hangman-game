package backend.academy.hangman.exception;

public class IncorrectGameStatusException extends RuntimeException {
    public IncorrectGameStatusException(String message) {
        super(message);
    }
}
