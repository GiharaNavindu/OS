package org.example;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {

    static String host = "localhost"; // Define the server address (localhost for local testing)
    static int port = 7999;

    // change the port as wished

    public static void main(String[] args) throws IOException {
        System.out.println("\t\tSimple Socket Client");
        System.out.println("\t\t=======================");

        // Connect to the server
        Socket socket = new Socket(host, port);
        System.out.println("Server is connected\n\n");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scannerInput = new Scanner(System.in);
            String messageFromServer = in.readLine();

            System.out.println("Message from Server: " + messageFromServer);

            // Chat loop
            String clientInput; // Variable to hold client input
            while (true) { // Infinite loop for chatting
                System.out.print("Client: "); // Prompt for client input
                clientInput = scannerInput.nextLine(); // Read input from the console
                out.println(clientInput); // Send client input to the server

                // Exit condition
                if (clientInput.equalsIgnoreCase("exit")) { // Check if the client wants to exit
                    System.out.println("Exiting chat..."); // Notify that the chat is ending
                    break; // Exit the loop
                }

                // Read response from server
                String responseFromServer = in.readLine(); // Read response from the server
                System.out.println("Message from Server: " + responseFromServer); // Print the server's response
            }

            scannerInput.close(); // Close the scanner after use
        } finally {
            // Close the socket
            socket.close(); // Close the socket connection to the server
        }
    }
}