package assign3.cghende1.bsse.asu.edu.android;

import android.os.AsyncTask;

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

public class APiBindings extends AsyncTask {

    @Override
    protected String doInBackground(Object[] params) {
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
            out.write("{ \"jsonrpc\": \"2.0\", \"method\": \"getNames\", \"params\": [ ], \"id\": 3}".getBytes());
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
