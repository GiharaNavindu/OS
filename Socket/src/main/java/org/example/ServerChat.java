package org.example;






import java.io.BufferedReader; // Import for reading input
import java.io.IOException; // Import for handling IO exceptions
import java.io.InputStreamReader; // Import for converting byte streams to character streams
import java.io.PrintWriter; // Import for writing output
import java.net.ServerSocket; // Import for server socket
import java.net.Socket; // Import for client socket
import java.util.Scanner; // Import for reading user input

public class ServerChat {

    static int port = 7999; // Define the port number for the server

    public static void main(String[] args) throws IOException { // Main method
        System.out.println("\t\tSimple Socket Server"); // Print server title
        System.out.println("\t\t======================"); // Print separator

        ServerSocket serversocket = new ServerSocket(port); // Create a server socket on the specified port
        Scanner scannerInput = new Scanner(System.in); // Create a scanner for user input
        String input, output; // Declare variables for input and output

        try {
            while (true) { // Infinite loop to accept multiple clients
                Socket socket = serversocket.accept(); // Accept incoming client connections
                System.out.println("Client is connected....\n\n"); // Notify that a client has connected

                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Create output stream to send messages to the client
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Create input stream to read messages from the client

                    try {
                        out.println("Welcome to the chat App"); // Send a welcome message to the client

                        while (true) { // Infinite loop to handle chat
                            input = in.readLine(); // Read input from the client
                            if (input == null || input.equalsIgnoreCase("exit")) { // Check if the client has disconnected or sent "exit"
                                System.out.println("Client has disconnected."); // Notify that the client has disconnected
                                break; // Exit the loop
                            }
                            System.out.println("Client Says: " + input); // Print the client's message

                            // The reply
                            System.out.print("Server: "); // Prompt for server response
                            output = scannerInput.nextLine(); // Read server response from console
                            out.println(output); // Send server response to the client
                        }
                    } finally {
                        socket.close(); // Close the client socket
                        out.close(); // Close the output stream
                        in.close(); // Close the input stream
                    }

                } finally {
                    socket.close(); // Close the socket after interaction
                }
            }
        } finally {
            serversocket.close(); // Close the server socket when done
        }
    }
}