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

import android.os.AsyncTask;

public class GetLibrary extends AsyncTask {

    MainActivity main;

    @Override
    protected PlaceLibrary doInBackground(Object ... params) {
        android.util.Log.w("Hello", "Getting things");
        this.main = (MainActivity) params[0];
        String placeNames = APIBindings.performRequest("{ \"jsonrpc\": \"2.0\", \"method\": \"getNames\", \"params\": [ ], \"id\": 3}", this.main);
        PlaceLibrary library = APIBindings.getPlaceLibrary(placeNames, this.main);
        return library;
    }

    @Override
    protected void onPostExecute(Object result) {
        PlaceLibrary library = (PlaceLibrary) result;
        main.initListView(library);
    }

}
