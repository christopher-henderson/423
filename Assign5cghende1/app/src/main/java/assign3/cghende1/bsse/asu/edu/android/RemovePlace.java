package assign3.cghende1.bsse.asu.edu.android;

import android.os.AsyncTask;

/**
 * Created by chris on 3/16/17.
 */

public class RemovePlace extends AsyncTask {

    modify modifyActivity;

    @Override
    public String doInBackground(Object ... params) {
        this.modifyActivity = (modify) params[0];
        PlaceDescription place = (PlaceDescription) params[1];
        String request = String.format("{ \"jsonrpc\": \"2.0\", \"method\": \"remove\", \"params\": [\"%s\"], \"id\": 3}", place.getName());
        APIBindings.performRequest(request, this.modifyActivity);
        return null;
    }

    @Override
    public void onPostExecute(Object result) {
        this.modifyActivity.doneSaving();
    }
}
