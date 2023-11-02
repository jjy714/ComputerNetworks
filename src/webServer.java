import java.io.* ;
import java.net.* ;
import java.util.* ;

public class WebServer  {
    public static void main(String argv[]) throws Exception {
        int port = 7030;
        ServerSocket connectionSocket = new ServerSocket(port);
        while(true){
            Socket socket = connectionSocket.accept();
            HttpRequest request = new HttpRequest(socket);
            Thread thread = new Thread(request);
            thread.start();
        }
        os.close();
        br.close();
        socket.close();

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
        DataOutputStream os = ;
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


}
