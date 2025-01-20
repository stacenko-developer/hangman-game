package backend.academy.hangman.exception;

public class IncorrectLetterException extends RuntimeException {
    public IncorrectLetterException(String message) {
        super(message);
    }
}
