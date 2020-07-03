import com.weather.Connect;

import java.io.IOException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try (Connect connect = new Connect("127.0.0.1", 40000)) {
            System.out.println("Connection with Server");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Make a Bet: ");


            Integer data = scanner.nextInt();



            String request = "" + data;

            System.out.println("Request: " + request);
            connect.write(request);

            String response = connect.read();

            System.out.println("Response: " + response);

            scanner.close();

            if (response.equals(1) ) {
                System.out.println("Win!" + "\n" + "You are Lucky!");
                System.out.println("");
            } else {
                System.out.println("Loose!" + "\n" + "Lucky Next Time!");
            }


            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}