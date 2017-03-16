package assign3.cghende1.bsse.asu.edu.android;

import android.os.AsyncTask;

/**
 * Created by chris on 3/16/17.
 */

public class GetLibrary extends AsyncTask {

    MainActivity main;

    @Override
    protected PlaceLibrary doInBackground(Object ... params) {
        android.util.Log.w("Hello", "Getting things");
        this.main = (MainActivity) params[0];
        String placeNames = APIBindings.performRequest("{ \"jsonrpc\": \"2.0\", \"method\": \"getNames\", \"params\": [ ], \"id\": 3}");
        PlaceLibrary library = APIBindings.getPlaceLibrary(placeNames);
        return library;
    }

    @Override
    protected void onPostExecute(Object result) {
        PlaceLibrary library = (PlaceLibrary) result;
        main.initListView(library);
    }

}
