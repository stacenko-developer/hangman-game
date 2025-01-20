package backend.academy.hangman.exception;

public class IncorrectWordLengthException extends RuntimeException {
    public IncorrectWordLengthException(String message) {
        super(message);
    }
}
