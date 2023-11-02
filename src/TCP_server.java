import java.net.*;
import java.io.*;


public class TCP_server {

    public static void main(String[] args) throws Exception {
        String Login, Password, state;
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();


            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            DataOutputStream outToClient2 = new DataOutputStream(connectionSocket.getOutputStream());
            outToClient.writeBytes("-----Welcome to the server!-----" + '\n');
            outToClient.writeBytes("Please enter your username: " + '\n');
            Login = inFromClient.readLine();
            outToClient.writeBytes("Please enter your password: " + '\n');
            Password = inFromClient.readLine();



            /*
            Create while loop to go over the number of tries
            Best to send a boolean signal to the client but can't
            Pass a char or string to the client to whether the login was successful or not.

            Be careful of keeping track of the number of data sent.
             */



            if (Login.equals("admin")|| Login.equals("Admin")) {
                if (Password.equals("admin") || Password.equals("Admin")) {
                    outToClient.writeBytes("You are now logged in as admin" + '\n');
                } else {
                    outToClient.writeBytes("Wrong password" +'\n');
                    outToClient.writeBytes("Good Bye" + '\n');
                    connectionSocket.close();
                }
            } else {
                    // If database is there, check if the username and password
                    // match with the server.
                    outToClient.writeBytes("No such Username" + '\n');
                    outToClient2.writeBytes("Good Bye" + '\n');
                    connectionSocket.close();
                    }

            outToClient.writeBytes("This is the inside server");
            outToClient2.writeBytes("Good Bye");
            connectionSocket.close();
            }
        }


        }


