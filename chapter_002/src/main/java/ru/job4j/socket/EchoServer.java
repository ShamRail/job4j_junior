package ru.job4j.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.logging.UsageLog4j;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.regex.Pattern;

public class EchoServer {

    private static final String HELL0 = "Hello";

    private static final String EXIT = "Exit";

    private static final Pattern DATA_PATTERN = Pattern.compile(".+\\?msg=.+ HTTP/1.1");

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    if (str != null && DATA_PATTERN.matcher(str).matches()) {
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
        } catch (Exception e) {
            LOG.error("Something went wrong ...", e);
        }
    }
}
