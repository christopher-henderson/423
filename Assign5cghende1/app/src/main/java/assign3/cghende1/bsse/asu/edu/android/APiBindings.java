package assign3.cghende1.bsse.asu.edu.android;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by chris on 3/13/17.
 */

public class APIBindings {

    public static PlaceLibrary getPlaceLibrary(String result) {
        PlaceLibrary library = new PlaceLibrary();
        try {
            JSONObject places = (JSONObject) new JSONTokener((String)result).nextValue();
            JSONArray placeNames = places.getJSONArray("result");
            for (int index = 0; index < placeNames.length(); index++) {
                String name = placeNames.getString(index);
                library.put(name, getPlace(name));
            }
        } catch (Exception e) {
            android.util.Log.w("Darn the luck", "DARN!");
            return null;
        }
        return library;
    }

    public static PlaceDescription getPlace(String name) {
        String request = String.format("{ \"jsonrpc\": \"2.0\", \"method\": \"get\", \"params\": [\"%s\"], \"id\": 3}", name);
        String response = performRequest(request);
        PlaceDescription place = null;
        JSONObject obj;
        try {
            obj = new JSONObject(response);
        } catch (Exception e) {
            android.util.Log.w("Failed PD", e);
            return place;
        }
        try {
            place = new PlaceDescription(obj.getJSONObject("result"));
        } catch (Exception e) {
            android.util.Log.w("Failed PD", e);
            return place;
        }
        return place;
    }

    public static String performRequest(String request) {
        StringBuilder outputBuilder = new StringBuilder();
        HttpURLConnection connection = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            URL url = new URL("http://10.0.2.2:8080/");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            out.write(request.getBytes());
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            char[] buff = new char[1024];
            int n;
            while ((n = in.read(buff)) > 0) {
                bos.write(new String(buff).getBytes(), 0, n);
            }
            bos.flush();
            bos.close();
            String result = bos.toString();
            android.util.Log.w("GOT IT", result);
            return result;
        } catch (Exception e) {
            android.util.Log.w("whoops", e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}

