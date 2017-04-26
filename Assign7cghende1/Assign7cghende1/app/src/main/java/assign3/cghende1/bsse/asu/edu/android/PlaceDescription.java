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

package assign3.cghende1.bsse.asu.edu.android;

import android.database.Cursor;

import org.json.JSONObject;

public class PlaceDescription {

    private int ID;
    private String name;
    private String description;
    private String category;
    private String address_title;
    private String address_street;
    private double elevation;
    private double latitude;
    private double longitude;
    private String image;

    public PlaceDescription(
            String name, String description, String category, String address_title,
            String address_street, double elevation, double latitude, double longitude, String image) {
        this.ID = 0;
        this.name = name;
        this.description = description;
        this.category = category;
        this.address_title = address_title;
        this.address_street = address_street;
        this.elevation = elevation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }

    public PlaceDescription(JSONObject obj) throws Exception {
        this.ID = 0;
        this.name = obj.getString("name");
        this.description = obj.getString("description");
        this.category = obj.getString("category");
        this.address_title = obj.getString("address-title");
        this.address_street = obj.getString("address-street");
        this.elevation = obj.getDouble("elevation");
        this.latitude = obj.getDouble("latitude");
        this.longitude = obj.getDouble("longitude");
        this.image = obj.getString("image");
    }

    public PlaceDescription(Cursor cursor) {
        this.ID = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.description = cursor.getString(2);
        this.category = cursor.getString(3);
        this.address_title = cursor.getString(4);
        this.address_street = cursor.getString(5);
        this.elevation = cursor.getDouble(6);
        this.longitude = cursor.getDouble(7);
        this.latitude = cursor.getDouble(8);
        this.image = cursor.getString(9);
    }

    public PlaceDescription(String obj) throws Exception {
        this(new JSONObject(obj));
    }

    public double greatCircleDistance(PlaceDescription other) {
//        var R = 6371e3; // metres
//        var φ1 = lat1.toRadians();
//        var φ2 = lat2.toRadians();
//        var Δφ = (lat2-lat1).toRadians();
//        var Δλ = (lon2-lon1).toRadians();
//
//        var a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
//                Math.cos(φ1) * Math.cos(φ2) *
//                        Math.sin(Δλ/2) * Math.sin(Δλ/2);
//        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//
//        var d = R * c;
        final double R = 6371e3;
        final double φ1 = Math.toRadians(this.latitude);
        final double φ2 = Math.toRadians(other.latitude);
        final double Δφ = Math.toRadians(other.latitude - this.latitude);
        final double Δλ = Math.toRadians(other.longitude - this.longitude);
        final double a = Math.sin(Δφ / 2.0) * Math.sin(Δφ / 2.0) +
                            Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ / 2.0) *
                                    Math.sin(Δλ / 2.0);
        final double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        // 0.000621371 Converts to miles.
        return R * c * 0.000621371;
    }

    public double bearing(PlaceDescription other) {
        double y = Math.sin(other.getLongitude()-this.getLongitude()) * Math.cos(other.getLatitude());
        double x = Math.cos(this.getLatitude())*Math.sin(other.getLatitude()) -
                Math.sin(this.getLatitude())*Math.cos(other.getLatitude())*Math.cos(other.getLongitude()-this.getLongitude());
        android.util.Log.w("asd", Double.toString(x));
        return Math.toDegrees(Math.atan2(y, x));
    }

    public String toJSON() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("name", this.name);
            obj.put("description", this.description);
            obj.put("category", this.category);
            obj.put("address-title", this.address_title);
            obj.put("address-street", this.address_street);
            obj.put("elevation", this.elevation);
            obj.put("latitude", this.latitude);
            obj.put("longitude", this.longitude);
            obj.put("image", this.image);
            return obj.toString(2);
        } catch (Exception err) {
            return err.toString();
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress_title() {
        return address_title;
    }

    public void setAddress_title(String address_title) {
        this.address_title = address_title;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getID() { return ID; }

    public void setID(int ID) { this.ID = ID; }
}
