package by.tms.calculator.console;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleReader {

    private final Scanner scanner = new Scanner(System.in);

    public double readNumber() {
        return scanner.nextDouble();
    }

    public String readOperation() {
        return scanner.next();
    }

    public String readCommand() {
        return scanner.next();
    }
}
