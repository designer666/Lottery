import com.lottery.Connect;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(40000)) {
            System.out.println("Server Started");

            while (true) {
                Connect connect = new Connect(serverSocket);

                new Thread(() -> {
                    System.out.println("Client Connected");

                    String request = connect.read();
                    System.out.println("Request: " + request);

                    String response = "Message from Server: " + Math.random();
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    connect.write(response);
                    System.out.println("Response: " + response);
                    try {
                        connect.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
