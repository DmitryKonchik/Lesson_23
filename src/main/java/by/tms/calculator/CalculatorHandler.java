package by.tms.calculator;

import by.tms.calculator.model.Operation;
import by.tms.calculator.service.OperationService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CalculatorHandler implements HttpHandler {

    OperationService operationService = new OperationService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

//        parseJSON(exchange);

        JSONCalculator(exchange);

    }

    private void parseJSON(HttpExchange exchange) throws IOException {
        FileReader fileReader = new FileReader("operations.json");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Optional<String> reduce = bufferedReader.lines().reduce(String::concat);
        if (reduce.isPresent()) {
            String json = reduce.get();
            exchange.getResponseHeaders().add("Content-Type", "text/json");
            exchange.sendResponseHeaders(200, json.length());
            exchange.getResponseBody().write(json.getBytes());
        }
        exchange.close();
    }

    private void JSONCalculator(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> parameters = parseQuery(query);
        Operation operation = new Operation();
        operation.setNum1(Double.parseDouble(parameters.get("num1")));
        operation.setNum2(Double.parseDouble(parameters.get("num2")));
        operation.setType(parameters.get("type"));

        Operation result = operationService.execute(operation);

        Gson gson = new Gson();
        String json = gson.toJson(result);

        exchange.sendResponseHeaders(200, json.length());

        exchange.getResponseBody().write(json.getBytes());

        exchange.close();
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> res = new HashMap<>();
        String[] split = query.split("&");
        for (String s : split) {
            String[] keyValue = s.split("=");
            res.put(keyValue[0], keyValue[1]);
        }
        return res;
    }
}
