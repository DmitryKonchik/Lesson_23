package by.tms.calculator;

import by.tms.calculator.console.ConsoleReader;
import by.tms.calculator.console.ConsoleReaderWithValidation;
import by.tms.calculator.console.ConsoleWriter;
import by.tms.calculator.model.Operation;
import by.tms.calculator.service.OperationService;

import java.util.List;

public class ConsoleApplication implements Application {

    private final ConsoleReader consoleReader = new ConsoleReaderWithValidation();
    private final ConsoleWriter consoleWriter = new ConsoleWriter();

    private final OperationService operationService = new OperationService();

    public void run() {
        while (true){
            consoleWriter.write("Enter num 1");
            double num1 = consoleReader.readNumber();
            consoleWriter.write("Enter num 2");
            double num2 = consoleReader.readNumber();
            consoleWriter.write("Enter operation type: SUM, SUB, MUL, DIV");
            String type = consoleReader.readOperation();

            Operation operation = new Operation(num1, num2, type);

            Operation execute = operationService.execute(operation);

            consoleWriter.write("Result: " + execute.getResult());

            consoleWriter.write("History: ");

            for (Operation o : operationService.getAllOperations()) {
                consoleWriter.write(String.format("Operation: %s %s %s %s",
                        o.getNum1(),
                        o.getNum2(),
                        o.getResult(),
                        o.getType()));
            }

            consoleWriter.write("Exit? [y/N]");

            String key = consoleReader.readCommand();

            if (key.equals("y")) {
                break;
            }
        }
    }
}
