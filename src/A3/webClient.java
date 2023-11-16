package A3;

import java.io.*;
import java.net.*;

public class webClient {

    public String getWebContentByGet(String urlString, final String charset, int timeout) throws IOException{
        if (urlString == null || urlString.length() == 0) {
            return null;
        }
        urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString : ("http://" + urlString).intern();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:5.0) Gecko/20100101 Firefox/5.0");
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
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:5.0) Gecko/20100101 Firefox/5.0");
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



}
