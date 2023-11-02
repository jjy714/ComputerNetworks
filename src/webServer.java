import java.io.* ;
import java.net.* ;
import java.util.* ;

public class WebServer  {
    public static void main(String argv[]) throws Exception {
        int port = 7030;
        while(true){
            Socket connectionSocket = welcomeSocket.accept();
            HttpRequest request = new HttpRequest(connectionSocket);
            Thread thread = new Thread(request);
            thread.start();
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
        InputStream is = ?;
        DataOutputStream os = ?;
        // Set up input stream filters.
        BufferedReader br = ?;
    }

    public void run() {
        try {
            processRequest();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    private void processRequest() throws Exception { . . . }
}
