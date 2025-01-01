package org.example;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String[] args) throws IOException {
        // Create a DatagramSocket to listen on port 4411
        DatagramSocket server = new DatagramSocket(4411);
        byte[] buffer = new byte[1024]; // Buffer to store received data

        System.out.println("Server is running and waiting for messages...");

        while (true) {
            // Create a DatagramPacket to receive data
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Receive the packet
            server.receive(packet);

            // Extract data from the packet
            String receivedMessage = new String(packet.getData(), 0, packet.getLength());

            // Print the received message
            System.out.println("Received message: " + receivedMessage);

            // Optionally, you can respond back to the client
            String response = "Message received: " + receivedMessage;
            byte[] responseBytes = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(
                    responseBytes,
                    responseBytes.length,
                    packet.getAddress(),
                    packet.getPort()
            );
            server.send(responsePacket);
        }
    }
}
