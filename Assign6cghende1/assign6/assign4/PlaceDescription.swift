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
 * @version April 9th, 2017
 */


import Foundation

class PlaceDescription {
    var name = "John Doe"
    var description = "No description available."
    var category = "N/A"
    var address_title = "N/A"
    var address_street = "N/A"
    var elevation = 0.0
    var latitude = 0.0
    var longitude = 0.0
    
    init() {
        self.name = "John Doe"
        self.description = "No description available."
        self.category = "N/A"
        self.address_title = "N/A"
        self.address_street = "N/A"
        self.elevation = 0.0
        self.latitude = 0.0
        self.longitude = 0.0
    }
    
    init(name: String, description: String, category: String, address_title: String, address_street: String, elevation: Double, latitude: Double, longitude: Double) {
        self.name = name
        self.description = description
        self.category = category
        self.address_title = address_title
        self.address_street = address_street
        self.elevation = elevation
        self.latitude = latitude
        self.longitude = longitude
    }
    
    func toJSON() -> String {
        let data: NSDictionary = [
            "name": self.name,
            "description": self.description,
            "category": self.category,
            "address_title": self.address_title,
            "address_street": self.address_street,
            "elevation": self.elevation,
            "latitude": self.latitude,
            "longitude": self.longitude
        ]
        do {
            let json = try JSONSerialization.data(withJSONObject: data, options: [JSONSerialization.WritingOptions.prettyPrinted])
            return NSString(data: json, encoding: String.Encoding.utf8.rawValue)! as String
        } catch {
            print("whoops")
            return "I screwed up."
        }
    }
    
    class func fromJSON(name:String, from json:[String:Any]) -> PlaceDescription {
        return PlaceDescription(
            name: name, description: json["description"]! as! String, category: json["category"]! as! String,
            address_title: json["address-title"]! as! String, address_street: json["address-street"]! as! String,
            elevation: json["elevation"]! as! Double, latitude: json["latitude"]! as! Double,
            longitude: json["longitude"]! as! Double
        )
    }
    
    class func fromJSON(name:String, from json:[String:String]) -> PlaceDescription {
        return PlaceDescription(
            name: name, description: json["description"]!, category: json["category"]!,
            address_title: json["address-title"]!, address_street: json["address-street"]!,
            elevation: Double(json["elevation"]!)!, latitude: Double(json["latitude"]!)!,
            longitude: Double(json["longitude"]!)!
        )
    }
    
}
