package by.tms.calculator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        ConsoleApplication consoleApplication = new ConsoleApplication();
//        consoleApplication.run();
        ServerApplication serverApplication = new ServerApplication();
        serverApplication.run();
    }
}