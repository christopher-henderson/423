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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class compare extends AppCompatActivity {

    PlaceLibrary placeLibrary;
    ListView placeList;
    PlaceDescription A;
    PlaceDescription B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        Intent intent = getIntent();
        this.placeLibrary = new PlaceLibrary(intent.getStringExtra("places"));
        this.placeList = (ListView) findViewById(R.id.placeList);
        this.A = null;
        this.B = null;
        initListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.A = null;
        this.B = null;
    }

    protected void initListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, this.placeLibrary.keys());
        this.placeList.setAdapter(adapter);
        final compare self = this;
        this.placeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long something){
                String name = (String) adapter.getItemAtPosition(position);
                android.util.Log.w("Clicked", name);
                android.util.Log.w("yo", Double.toString(self.placeLibrary.get(name).getLatitude()));
                if (self.A == null) {
                    self.A = self.placeLibrary.get(name);
                } else {
                    self.B = self.placeLibrary.get(name);
                    self.showComparison();
                }
            }
        });
    }

    protected void showComparison() {
        Intent intent = new Intent(this, ShowComparison.class);
        intent.putExtra("A", this.A.toJSON());
        intent.putExtra("B", this.B.toJSON());
        startActivity(intent);
    }
}
