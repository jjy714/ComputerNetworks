package A3;

import java.net.*;
import java.util.*;
import java.io.*;

public final class WebServer2 {
    public static void main(String[] argv) throws Exception {

        try {
            // Could get the port number from the command line.
//    		int port = (new Integer(argv[0])).intValue();    		

            // Establish the listen socket.
            ServerSocket serversocket = new ServerSocket(7030);
            // Process HTTP service requests in an infinite loop.
            while (true){
                // Listen for a TCP connection request.
                Socket socket = serversocket.accept();

                // Construct an object to process the HTTP request message.
                HttpRequest2 request = new HttpRequest2(socket);
                webClient client = new webClient();

                // Create a new thread to process the request.
                Thread thread = new Thread(request);
                // Start the thread.
                thread.start();
            }

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }
}