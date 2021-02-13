package com.nthily.note.Utilities;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is;
        URL urll = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urll.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Connection", "Keep-Alive");
        connection.connect();
        if(connection.getResponseCode()!=HttpURLConnection.HTTP_OK && connection.getResponseCode()!=HttpURLConnection.HTTP_CREATED && connection.getResponseCode()!=HttpURLConnection.HTTP_ACCEPTED) {
            is = connection.getErrorStream();
        } else {
            is = connection.getInputStream();
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
