package assign3.cghende1.bsse.asu.edu.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    PlaceLibrary placeLibrary;
    Spinner placeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.placeSpinner = (Spinner) findViewById(R.id.placeSpinner);
        InputStream is = this.getApplicationContext().getResources().openRawResource(R.raw.places);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String placesString = "";
        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                placesString = placesString + line;
            }
        } catch (Exception ex) {
            android.util.Log.w("nope", ex);
        }
        try {

            JSONObject places = (JSONObject) new JSONTokener(placesString).nextValue();
            this.placeLibrary = new PlaceLibrary(places);
        } catch (Exception ex) {
            android.util.Log.w("Darn", ex);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, this.placeLibrary.keys());
        this.placeSpinner.setAdapter(adapter);
    }
}
