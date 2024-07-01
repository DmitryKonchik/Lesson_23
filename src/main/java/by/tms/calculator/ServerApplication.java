package by.tms.calculator;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerApplication implements Application{

    @Override
    public void run() throws IOException {

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080),0);
        httpServer.createContext("/calculator", new CalculatorHandler());
        httpServer.start();
    }
}
