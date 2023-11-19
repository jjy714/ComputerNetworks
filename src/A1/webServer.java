package A1;

import java.io.* ;
import java.net.* ;
import java.util.* ;

public class webServer {
    public static void main(String argv[]) throws Exception {
        int port = 7030;
        ServerSocket connectionSocket = new ServerSocket(port);
        while(true) {
            Socket socket = connectionSocket.accept();
            HttpRequest request = new HttpRequest(socket);
            request.run();

        }

    }
}


    final class HttpRequest implements Runnable {
        final static String CRLF = "\r\n";
        Socket socket;

        // Constructor
        public HttpRequest(Socket socket) throws Exception {
            this.socket = socket;
        }
        // Implement the run() method of the Runnable interface.

        private void processRequest() throws Exception {
            // Get a reference to the socket's input and output streams.
            InputStream is = socket.getInputStream();
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            // Set up input stream filters.
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String clientMessage = br.readLine();
            // Example: Writing a response to the client
            System.out.println(clientMessage);
            os.write(clientMessage.getBytes());

            String headerLine = null;
            while ((headerLine = br.readLine()).length() != 0) {
                System.out.println(headerLine);
            }

            // Close the resources
            br.close();
            is.close();
            os.close();
        }

        public void run() {
            try {
                processRequest();
            } catch (Exception e) {
                System.out.println(e);
            }

        }


    }

