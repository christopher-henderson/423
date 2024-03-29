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
 * @version March 3rd, 2017
 */

import Foundation
import UIKit

class PlaceLibrary : UITabBarController {
    var places:[PlaceDescription] = PlaceLibrary.initPlaces([
        "ASU-West" :
        [
            "address-title" : "ASU West Campus",
            "address-street" : "13591 N 47th Ave$Phoenix AZ 85051",
            "elevation" : "1100.0",
            "latitude" : "33.608979",
            "longitude" : "-112.159469",
            "name" : "ASU-West",
            "image" : "asuwest",
            "description" : "Home of ASU's Applied Computing Program",
            "category" : "School"
        ],
        "UAK-Anchorage" :
        [
            "address-title" : "University of Alaska at Anchorage",
            "address-street" : "290 Spirit Dr$Anchorage AK 99508",
            "elevation" : "0.0",
            "latitude" : "61.189748",
            "longitude" : "-149.826721",
            "name" : "UAK-Anchorage",
            "image" : "univalaska",
            "description" : "University of Alaska's largest campus",
            "category" : "School"
        ],
        "Toreros" :
        [
            "address-title" : "University of San Diego",
            "address-street" : "5998 Alcala Park$San Diego CA 92110",
            "elevation" : "200.0",
            "latitude" : "32.771923",
            "longitude" : "-117.188204",
            "name" : "Toreros",
            "image" : "univsandiego",
            "description" : "The University of San Diego, a private Catholic undergraduate university.",
            "category" : "School"
        ],
        "Barrow-Alaska" :
        [
            "address-title" : "Will Rogers Airport",
            "address-street" : "1725 Ahkovak St$Barrow AK 99723",
            "elevation" : "5.0",
            "latitude" : "71.287881",
            "longitude" : "-156.779417",
            "name" : "Barrow-Alaska",
            "image" : "barrowalaska",
            "description" : "The only real way in and out of Barrow Alaska.",
            "category" : "Travel"
        ],
        "Calgary-Alberta" :
        [
            "address-title" : "Calgary International Airport",
            "address-street" : "2000 Airport Rd NE$Calgary AB T2E 6Z8 Canada",
            "elevation" : "3556.0",
            "latitude" : "51.131377",
            "longitude" : "-114.011246",
            "name" : "Calgary-Alberta",
            "image" : "calgaryalberta",
            "description" : "The home of the Calgary Stampede Celebration",
            "category" : "Travel"
        ],
        "London-England" :
        [
            "address-title" : "Renaissance London Heathrow Airport",
            "address-street" : "5 Mondial Way$Harlington Hayes UB3 UK",
            "elevation" : "82.0",
            "latitude" : "51.481959",
            "longitude" : "-0.445286",
            "name" : "London-England",
            "image" : "londonunderground",
            "description" : "Renaissance Hotel at the Heathrow Airport",
            "category" : "Travel"
        ],
        "Moscow-Russia" :
        [
            "address-title" : "Courtyard Moscow City Center",
            "address-street" : "Voznesenskiy per 7 $ Moskva Russia 125009",
            "elevation" : "512.0",
            "latitude" : "55.758666",
            "longitude" : "37.604058",
            "name" : "Moscow-Russia",
            "image" : "moscow",
            "description" : "The Marriott Courtyard in downtown Moscow",
            "category" : "Travel"
        ],
        "New-York-NY" :
        [
            "address-title" : "New York City Hall",
            "address-street" : "1 Elk St$New York NY 10007",
            "elevation" : "2.0",
            "latitude" : "40.712991",
            "longitude" : "-74.005948",
            "name" : "New-York-NY",
            "image" : "newyork",
            "description" : "New York City Hall at West end of Brooklyn Bridge",
            "category" : "Travel"
        ],
        "Notre-Dame-Paris" :
        [
            "address-title" : "Cathedral Notre Dame de Paris",
            "address-street" : "6 Parvis Notre-Dame Pl Jean-Paul-II$75004 Paris France",
            "elevation" : "115.0",
            "latitude" : "48.852972",
            "longitude" : "2.349910",
            "name" : "Notre-Dame-Paris",
            "image" : "notredame3rdstatuewest",
            "description" : "The 13th century cathedral with gargoyles, one of the first flying buttress, and home of the purported crown of thorns.",
            "category" : "Travel"
        ],
        "Circlestone" :
        [
            "address-title" : "",
            "address-street" : "",
            "elevation" : "6000.0",
            "latitude" : "33.477524",
            "longitude" : "-111.134345",
            "name" : "Circlestone",
            "image" : "circlestone",
            "description" : "Indian Ruins located on the second highest peak in the Superstition Wilderness of the Tonto National Forest. Leave Fireline at Turney Spring (33.487610,-111.132400)",
            "category" : "Hike"
        ],
        "Reavis-Ranch" :
        [
            "address-title" : "",
            "address-street" : "",
            "elevation" : "5000.0",
            "latitude" : "33.491154",
            "longitude" : "-111.155385",
            "name" : "Reavis-Ranch",
            "image" : "reavisranch",
            "description" : "Historic Ranch in Superstition Mountains famous for Apple orchards",
            "category" : "Hike"
        ],
        "Rogers-Trailhead" :
        [
            "address-title" : "",
            "address-street" : "",
            "elevation" : "4500.0",
            "latitude" : "33.422212",
            "longitude" : "-111.173393",
            "name" : "Rogers-Trailhead",
            "image" : "rogerstrough",
            "description" : "Trailhead for hiking to Rogers Canyon Ruins and Reavis Ranch",
            "category" : "Hike"
        ],
        "Reavis-Grave" :
        [
            "address-title" : "",
            "address-street" : "",
            "elevation" : "3900.0",
            "latitude" : "33.441499",
            "longitude" : "-111.182511",
            "name" : "Reavis-Grave",
            "image" : "reavisgrave",
            "description" : "Grave site of Reavis Ranch Proprietor.",
            "category" : "Hike"
        ],
        "Muir-Woods" : 
        [
            "address-title" : "Muir Woods National Monument",
            "address-street" : "1 Muir Woods Rd$Mill Valley CA 94941",
            "elevation" : "350.0",
            "latitude" : "37.8912",
            "longitude" : "-122.5957",
            "name" : "Muir-Woods",
            "image" : "muirwoods",
            "description" : "Redwood forest North of San Francisco, surrounded by Mount Tamalpais State Park.",
            "category" : "Hike"
        ],
        "Windcave-Peak" : 
        [
            "address-title" : "Usery Mountain Recreation Area",
            "address-street" : "3939 N Usery Pass Rd$Mesa AZ 85207",
            "elevation" : "3130.0",
            "latitude" : "33.476069",
            "longitude" : "-111.595709",
            "name" : "Windcave-Peak",
            "image" : "windcavepeak",
            "description" : "Beyond the Wind Cave is a half mile trail with 250' additional elevation to the peak overlooking Usery and the Valley.",
            "category" : "Hike"
        ]
        ])
    
    var selected = 0
    
    class func initPlaces(_ initialPlaces: [String:[String:String]]) -> [PlaceDescription] {
        var p = [PlaceDescription]()
        for (name, obj) in initialPlaces {
            p.append(PlaceDescription.fromJSON(name: name, from: obj))
        }
        return p
    }
    
    func addPlace(_ place:PlaceDescription) {
        self.places.insert(place, at: 0)
    }
    
    func count() -> Int {
        return self.places.count
    }
    
    func get(index i:Int) -> PlaceDescription {
        if i < self.count() {
            return self.places[i]
        } else {
            return PlaceDescription()
        }
    }
    
    func deletePlace(index i:Int) {
        self.places.remove(at: i)
    }
    
}
