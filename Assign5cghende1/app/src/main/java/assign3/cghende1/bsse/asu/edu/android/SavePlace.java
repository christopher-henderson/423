package assign3.cghende1.bsse.asu.edu.android;

import android.os.AsyncTask;

/**
 * Created by chris on 3/16/17.
 */

public class SavePlace extends AsyncTask {

    Saver activity;

    @Override
    protected PlaceLibrary doInBackground(Object ... params) {
        android.util.Log.w("Hello", "Getting things");
        this.activity = (Saver) params[0];
        PlaceDescription place = (PlaceDescription) params[1];
        APIBindings.savePlaceDescription(place);
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        this.activity.doneSaving();
    }
}
