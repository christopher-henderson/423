/*
 * MIT License
 *
 * Copyright (c) 2017 Christopher Henderson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/*
 * @author Christoper Henderson mailto:chris@chenderson.org
 * @version March 21st, 2017
 */

package assign3.cghende1.bsse.asu.edu.android;

import android.app.Activity;

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

public class APIBindings {

    public static PlaceLibrary getPlaceLibrary(String result, Activity activity) {
        PlaceLibrary library = new PlaceLibrary();
        try {
            JSONObject places = (JSONObject) new JSONTokener((String)result).nextValue();
            JSONArray placeNames = places.getJSONArray("result");
            for (int index = 0; index < placeNames.length(); index++) {
                String name = placeNames.getString(index);
                library.put(name, getPlace(name, activity));
            }
        } catch (Exception e) {
            android.util.Log.w("Darn the luck", "DARN!");
            return null;
        }
        return library;
    }

    public static PlaceDescription getPlace(String name, Activity activity) {
        String request = String.format("{ \"jsonrpc\": \"2.0\", \"method\": \"get\", \"params\": [\"%s\"], \"id\": 3}", name);
        String response = performRequest(request, activity);
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

    public static void savePlaceDescription(PlaceDescription place, Activity activity) {
        JSONObject obj = new JSONObject();
        String request = String.format("{ \"jsonrpc\": \"2.0\", \"method\": \"add\", \"params\": [%s], \"id\": 3}", place.toJSON());
        performRequest(request, activity);
    }

    public static String performRequest(String request, Activity activity) {
        StringBuilder outputBuilder = new StringBuilder();
        HttpURLConnection connection = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            URL url = new URL(activity.getResources().getString((R.string.api_url)));
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

