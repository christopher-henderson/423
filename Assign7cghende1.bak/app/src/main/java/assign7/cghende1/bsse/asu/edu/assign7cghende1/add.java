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

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class add extends AppCompatActivity {

    assign3.cghende1.bsse.asu.edu.android.PlaceLibrary placeLibrary;
    PlaceDescription place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent intent = getIntent();
        this.placeLibrary = new assign3.cghende1.bsse.asu.edu.android.PlaceLibrary(intent.getStringExtra("places"));
    }

    public void save() {
        EditText name = (EditText) findViewById(R.id.name);
        EditText address_title = (EditText) findViewById(R.id.address_title);
        EditText address_street = (EditText) findViewById(R.id.address_street);
        EditText elevation = (EditText) findViewById(R.id.elevation);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);
        EditText category = (EditText) findViewById(R.id.category);
        EditText description = (EditText) findViewById(R.id.description);
        EditText image = (EditText) findViewById(R.id.image);

        try {
            this.place = new assign3.cghende1.bsse.asu.edu.android.PlaceDescription(
                    name.getText().toString(),
                    description.getText().toString(),
                    category.getText().toString(),
                    address_title.getText().toString(),
                    address_street.getText().toString(),
                    new Double(elevation.getText().toString()),
                    new Double(latitude.getText().toString()),
                    new Double(longitude.getText().toString()),
                    image.getText().toString()
            );
        } catch (java.lang.NumberFormatException ex) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Elevtion, latitude, and longitude must be numbers.")
                    .setTitle("Invalid place description.");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        this.placeLibrary.put(this.place.getName(), this.place);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("places", this.placeLibrary.toJSON());
        startActivity(intent);
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
        inflater.inflate(R.menu.add_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
 * Implement onOptionsItemSelected(MenuItem item){} to handle clicks of buttons that are
 * in the action bar.
 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected()");
        Intent intent = new Intent(this, MainActivity.class);
        switch (item.getItemId()) {
            case R.id.confirm_add:
                this.save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
