package assign3.cghende1.bsse.asu.edu.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    PlaceLibrary placeLibrary;
    ListView placeList;

    static boolean firstOnCreate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (firstOnCreate) {
            init();
            firstOnCreate = false;
        } else {
            initFromIntent();
        }
    }

    protected void init() {
        this.placeList = (ListView) findViewById(R.id.placeList);
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
        this.placeList.setAdapter(adapter);


        final MainActivity self = this;
        this.placeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long something){
                String name = (String) adapter.getItemAtPosition(position);
                Intent intent = new Intent(self, modify.class);
                intent.putExtra("place", self.placeLibrary.get(name).toJSON());
                startActivity(intent);
            }
        });
    }

    protected void initFromIntent() {

    }
}
