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
 * @version January 18th, 2017
 */

package assign3.cghende1.bsse.asu.edu.android;

import org.json.JSONObject;

public class PlaceDescription {

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

    public double greatCircleDistance(PlaceDescription other) {
        return Math.atan(
                Math.sqrt(
                        Math.pow(Math.cos(other.getLatitude()) * Math.sin(this.longitude), 2) +
                                Math.pow(Math.cos(this.latitude) * Math.sin(other.getLatitude()) -
                                        Math.sin(this.latitude) * Math.cos(other.getLatitude()) * Math.cos(this.longitude) , 2)
                )
                        /
                        (
                                Math.sin(this.latitude) * Math.sin(other.getLatitude()) +
                                        Math.cos(this.latitude) * Math.cos(other.getLatitude() * Math.cos(this.longitude)))
        );
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
}
