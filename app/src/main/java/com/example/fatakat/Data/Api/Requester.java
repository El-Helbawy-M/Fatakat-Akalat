package com.example.fatakat.Data.Api;

import com.example.fatakat.Data.Models.Bad;
import com.example.fatakat.Data.Models.Good;
import com.example.fatakat.Data.Models.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class Requester {
    static HttpURLConnection connector;
    static String url;
    public Requester(String url){
        this.url = url;
    }
    private static void _setupConnection(){
        try {
            connector = (HttpURLConnection) new URL(url).openConnection(); // opening a connection
            connector.setRequestMethod("GET"); // determining the request method
            connector.setConnectTimeout(5000); // determining the connection time out in milliseconds
            connector.setReadTimeout(5000); // determining the reading from connection time out milliseconds
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Result getRequest() throws IOException {
        Result result;
        int code = 0;
        InputStream inputStream = null;
        _setupConnection();
        try {
            connector.connect();
            code = connector.getResponseCode();
            if(code == 200){
                inputStream = connector.getInputStream();
                result = new Good<String>(code,_readFromStream(inputStream));
            }
            else {
                result = new Bad(code, connector.getResponseMessage());
            }
        } catch (IOException e) {
            result = new Bad(code,e.getMessage());
            e.printStackTrace();
        }
        finally {
                if(connector != null) connector.disconnect();
                if (inputStream != null) inputStream.close();
        }
        return result;
    }
    private static String _readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
