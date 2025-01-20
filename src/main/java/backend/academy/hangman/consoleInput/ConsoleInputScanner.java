package backend.academy.hangman.consoleInput;

import java.nio.charset.Charset;
import java.util.Scanner;

public class ConsoleInputScanner implements InputScanner {

    private final Scanner scanner = new Scanner(System.in, Charset.defaultCharset());

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
