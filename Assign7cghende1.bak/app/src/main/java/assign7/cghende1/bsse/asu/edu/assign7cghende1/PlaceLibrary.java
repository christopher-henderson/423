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

import android.app.Application;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chris on 2/6/17.
 */

public class PlaceLibrary extends Application {
    HashMap<String, PlaceDescription> places;

    PlaceLibrary(JSONObject places) {
        this.places = new HashMap<String, PlaceDescription>();
        Iterator<String> keys = places.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            try {
                JSONObject placeJSON = (JSONObject) places.get(key);
                PlaceDescription place = new PlaceDescription(placeJSON);
                this.places.put(key, place);
            } catch (Exception ex) {
                android.util.Log.w("Darn", ex);
            }
        }
    }

    PlaceLibrary(String places) {
        this.places = new HashMap<String, PlaceDescription>();
        try {
            JSONObject obj = new JSONObject(places);
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject placeJSON = new JSONObject((String)obj.get(key));
                this.places.put(key, new PlaceDescription(placeJSON));
            }
        } catch (Exception ex) {
            android.util.Log.w("Failed to deserialize.", ex);
        }
    }

    public String toJSON() {
        HashMap<String, String> newMap = new HashMap<String, String>();
        for (String key : this.places.keySet()) {
            newMap.put(key, this.places.get(key).toJSON());
        }
        return new JSONObject(newMap).toString();
    }

    public ArrayList<PlaceDescription> values() {
        return (ArrayList<PlaceDescription>) places.values();
    }

    public List<String> keys() {
        return new ArrayList<String>(this.places.keySet());
    }

    public PlaceDescription get(String name) {
        return this.places.get(name);
    }

    public void remove(String name) {
        this.places.remove(name);
    }

    public void put(String key, PlaceDescription place) {
        this.places.put(key, place);
    }
}
