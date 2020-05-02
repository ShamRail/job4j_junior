package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final String HELL0 = "Hello";

    private static final String EXIT = "Exit";

    private static final String DATA_PATTERN = ".+\\?msg=.+ HTTP/1.1";

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    if (str != null && str.matches(DATA_PATTERN)) {
                        String argument = str.substring(
                                str.lastIndexOf('=') + 1,
                                str.lastIndexOf(' ')
                        );
                        String answer = HELL0.equalsIgnoreCase(argument) ? "Hello" : argument;
                        if (EXIT.equalsIgnoreCase(answer)) {
                            answer = "Server stopped!";
                        }
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write(answer.getBytes());
                        if (EXIT.equalsIgnoreCase(argument)) {
                            server.close();
                            break;
                        }
                    }
                }
            }
        }
    }
}
