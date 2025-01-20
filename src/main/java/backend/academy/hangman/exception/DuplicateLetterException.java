package backend.academy.hangman.exception;

public class DuplicateLetterException extends RuntimeException {
    public DuplicateLetterException(String message) {
        super(message);
    }
}
