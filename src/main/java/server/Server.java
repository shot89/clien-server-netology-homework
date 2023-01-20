package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server started");

        int port = 8089;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.println("New connection accepted. Port: " + clientSocket.getPort());

                    out.println("Write your name");

                    final String name = in.readLine();

                    out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

                    out.println(String.format("How old are you?"));

                    String inp = in.readLine();
                    try {
                        int age = Integer.parseInt(inp);
                        if (age < 18) out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                        else
                            out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                    } catch (NumberFormatException e) {
                        out.println("Error input age value");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
