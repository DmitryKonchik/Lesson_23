package by.tms.calculator.service;

import by.tms.calculator.model.Operation;
import by.tms.calculator.storage.FileOperationStorage;
import by.tms.calculator.storage.OperationStorage;

import java.util.List;

public class OperationService {

    private final OperationStorage operationStorage = new FileOperationStorage();

    public Operation execute(Operation operation) {
        switch (operation.getType()) {
            case "SUM":
                operation.setResult(operation.getNum1() + operation.getNum2());
                operationStorage.save(operation);
                return operation;
            case "SUB":
                operation.setResult(operation.getNum1() - operation.getNum2());
                operationStorage.save(operation);
                return operation;
            case "MUL":
                operation.setResult(operation.getNum1() * operation.getNum2());
                operationStorage.save(operation);
                return operation;
            case "DIV":
                operation.setResult(operation.getNum1() / operation.getNum2());
                operationStorage.save(operation);
                return operation;
        }
        throw new IllegalArgumentException("by.tms.calculator.model.Operation type not supported");
    }

    public List<Operation> getAllOperations() {
        return operationStorage.findAll();
    }

}
