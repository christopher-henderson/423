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
    
    var places:[PlaceDescription] = []
    var tableView: UITableViewController? = nil
    
    var selected = 0
    
    func setTableViewController(view: UITableViewController) {
        self.tableView = view
    }
    
    func load() {
        self.places.removeAll()
        self.tableView!.tableView!.reloadData()
        APIBindings.getPlaces(completion: self.addPlaces, done: self.tableView!.tableView!.reloadData)
    }
    
    func addPlace(_ place:PlaceDescription) {
        print("Adding")
        self.places.insert(place, at: 0)
    }
    
    func addPlaces(places: [PlaceDescription]) {
        print("Adding multiple places")
        print(places.count)
        for place in places {
            self.addPlace(place)
        }
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
