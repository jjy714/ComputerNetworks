import java.io.*;
import java.net.*;
import java.util.Objects;


public class TCP_client {
    public static void main(String[] args) throws Exception {
        String UserID, Password, serverMessage, state;
        boolean login = false;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader inFromServer2 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println(inFromServer.readLine());
        System.out.print(inFromServer.readLine());
        UserID = inFromUser.readLine(); // Read user input
        outToServer.writeBytes(UserID + '\n');
        System.out.print(inFromServer.readLine());
        Password = inFromUser.readLine(); // Read user input
        outToServer.writeBytes(Password + '\n');

        /*
        Use while loop to keep collecting state from server
        If server sends pass
        go over to printing the results
        else if the result is not pass
        keep looping until the result is correct or the while loop as failed.

        */
        state = inFromServer.readLine();

        if (state.equals("You are now logged in as admin")) {
            System.out.println(inFromServer.readLine());
            System.out.println(inFromServer2.readLine());
        } else{
            System.out.println(inFromServer.readLine());
            System.out.println(inFromServer2.readLine());
        }
        clientSocket.close();
    }
}

