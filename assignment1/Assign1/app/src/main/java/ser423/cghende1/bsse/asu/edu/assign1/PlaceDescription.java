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

package ser423.cghende1.bsse.asu.edu.assign1;

import java.lang.Math;

public class PlaceDescription {

    private String name;
    private String description;
    private String category;
    private String address_title;
    private String address_street;
    private double elevation;
    private double latitude;
    private double longitude;

    public PlaceDescription(
            String name, String description, String category, String address_title,
            String address_street, double elevation, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.address_title = address_title;
        this.address_street = address_street;
        this.elevation = elevation;
        this.latitude = latitude;
        this.longitude = longitude;
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

}
