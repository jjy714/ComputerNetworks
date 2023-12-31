package A3;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class webClient {

    public String getWebContentByGet(String urlString, final String charset, int timeout) throws IOException{
        if (urlString == null || urlString.length() == 0) {
            return null;
        }
        urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString : ("http://" + urlString).intern();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "9918920236/JUNYOUNGJUNG/WEBCLIENT/COMPUTERNETWORK");
        conn.setRequestProperty("Accept", "text/html");
        conn.setConnectTimeout(timeout);

        try {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        InputStream input = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, charset));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while((line = reader.readLine()) != null){
            sb.append(line).append("\r\n");
        }
        if(reader != null){
            reader.close();
        }
        if(conn != null){
            conn.disconnect();
        }
        return sb.toString();
    }
    public void getWebContentByGetImage(String urlString, int timeout) throws IOException{
        if (urlString == null || urlString.length() == 0) {
            return;
        }
        urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString : ("http://" + urlString).intern();
        URL url = new URL(urlString);
        Image image = null;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "9918920236/JUNYOUNGJUNG/WEBCLIENT/COMPUTERNETWORK");
        conn.setRequestProperty("Accept", "image/jpeg");
        conn.setConnectTimeout(timeout);

        try {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return;
            }
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        image = ImageIO.read(url);
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);

        if(conn != null){
            conn.disconnect();
        }
    }

    public String getWebContentByPost(String urlString, String data, final String charset, int timeout) throws IOException{
        if(urlString == null || urlString.length() == 0){
            return null;
        }
        urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString : ("http://" + urlString).intern();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
        conn.setRequestProperty("User-Agent", "9918920236/JUNYOUNGJUNG/WEBCLIENT/COMPUTERNETWORK");
        conn.setRequestProperty("Accept", "text/xml");
        conn.setConnectTimeout(timeout);
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());

        byte[] content = data.getBytes("UTF-8");

        out.write(content);
        out.flush();
        out.close();

        try{
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while((line = reader.readLine()) != null){
            sb.append(line).append("\r\n");
        }
        if(reader != null){
            reader.close();
        }
        if(conn != null){
            conn.disconnect();
        }
        return sb.toString();
    }
    public static void main(String args[]) throws IOException {
        int command = 0;
        String newUrl = "";
        String newData = "";
        Scanner scanner = new Scanner(System.in);
        webClient webClient = new webClient();

        System.out.print("Select the command (1: GET, 2: POST, 3:IMAGE, 0: Exit): ");
        command = scanner.nextInt();
        System.out.print("Enter the URL: ");
        newUrl = scanner.next();
        if (command == 1) {
            System.out.println(webClient.getWebContentByGet(newUrl, "UTF-8", 10000));
            System.out.println("Done");
        }
        if(command == 2){
            System.out.print("Enter the data: ");
            newData = scanner.next();
            System.out.println(webClient.getWebContentByPost(newUrl, newData, "UTF-8", 10000));
        }
        if(command == 3){
            webClient.getWebContentByGetImage(newUrl, 10000);
        }
        if(command == 0){
            System.exit(0);
        }
    }
}
