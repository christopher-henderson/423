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
 * @version February 10th, 2017
 */

package assign7.cghende1.bsse.asu.edu.assign7cghende1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        this.placeList = (ListView) findViewById(R.id.placeList);
        if (this.firstOnCreate) {
            this.init();
            this.firstOnCreate = false;
        } else {
            this.initFromIntent();
        }
        this.initListView();
    }

        /*
     * One way to create Aciton Bar Buttons is to use xml menu specification. Create the file:
     * res/menu/main_activity_actions.xml to include contents as in this project.
     * reference to any images for the action bar should be created by right clicking on res folder
     * in the project and creating a new image asset. Be sure to specify Action Bar & Tab Icon
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
 * Implement onOptionsItemSelected(MenuItem item){} to handle clicks of buttons that are
 * in the action bar.
 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected()");
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add_place:
                intent = new Intent(this, add.class);
                intent.putExtra("places", this.placeLibrary.toJSON());
                startActivity(intent);
                return true;
            case R.id.compare:
                intent = new Intent(this, compare.class);
                intent.putExtra("places", this.placeLibrary.toJSON());
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void init() {
        InputStream is = this.getApplicationContext().getResources().openRawResource(places);
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
    }

    protected void initFromIntent() {
        Intent intent = getIntent();
        this.placeLibrary = new PlaceLibrary(intent.getStringExtra("places"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, this.placeLibrary.keys());
        this.placeList.setAdapter(adapter);
    }

    protected void initListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, this.placeLibrary.keys());
        this.placeList.setAdapter(adapter);
        final MainActivity self = this;
        this.placeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long something){
                String name = (String) adapter.getItemAtPosition(position);
                Intent intent = new Intent(self, modify.class);
                intent.putExtra("places", self.placeLibrary.toJSON());
                intent.putExtra("place", name);
                startActivity(intent);
            }
        });
    }
}
