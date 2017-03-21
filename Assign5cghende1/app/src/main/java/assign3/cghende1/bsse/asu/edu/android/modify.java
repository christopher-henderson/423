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
import android.widget.EditText;

public class modify extends AppCompatActivity implements Saver {

    PlaceLibrary placeLibrary;
    PlaceDescription place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        Intent intent = getIntent();
        this.placeLibrary = new PlaceLibrary(intent.getStringExtra("places"));
        this.place = this.placeLibrary.get(intent.getStringExtra("place"));
        EditText name = (EditText) findViewById(R.id.name);
        EditText address_title = (EditText) findViewById(R.id.address_title);
        EditText address_street = (EditText) findViewById(R.id.address_street);
        EditText elevation = (EditText) findViewById(R.id.elevation);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);
        EditText category = (EditText) findViewById(R.id.category);
        EditText description = (EditText) findViewById(R.id.description);
        EditText image = (EditText) findViewById(R.id.image);

        name.setText(this.place.getName());
        address_title.setText(this.place.getAddress_title());
        address_street.setText(this.place.getAddress_street());
        elevation.setText(new Double(this.place.getElevation()).toString());
        latitude.setText(new Double(this.place.getLatitude()).toString());
        longitude.setText(new Double(this.place.getLongitude()).toString());
        category.setText(this.place.getCategory());
        description.setText(this.place.getDescription());
        image.setText(this.place.getImage());
    }

    public void save(View v) {
        EditText name = (EditText) findViewById(R.id.name);
        EditText address_title = (EditText) findViewById(R.id.address_title);
        EditText address_street = (EditText) findViewById(R.id.address_street);
        EditText elevation = (EditText) findViewById(R.id.elevation);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);
        EditText category = (EditText) findViewById(R.id.category);
        EditText description = (EditText) findViewById(R.id.description);
        EditText image = (EditText) findViewById(R.id.image);

//        this.placeLibrary.remove(this.place.getName());

        this.place.setName(name.getText().toString());
        this.place.setAddress_title(address_title.getText().toString());
        this.place.setAddress_street(address_street.getText().toString());
        this.place.setElevation(new Double(elevation.getText().toString()));
        this.place.setLatitude(new Double(latitude.getText().toString()));
        this.place.setLongitude(new Double(longitude.getText().toString()));
        this.place.setCategory(category.getText().toString());
        this.place.setDescription(description.getText().toString());
        this.place.setImage(image.getText().toString());

//        this.placeLibrary.put(this.place.getName(), this.place);
        new SavePlace().execute(this, this.place);
    }

    public void doneSaving() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void delete(View v) {
        new RemovePlace().execute(this, this.place);
    }
}
