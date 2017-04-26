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
 * @version April 28th, 2017
 */

import UIKit
import CoreData

class Database {
    
    var mContext:NSManagedObjectContext?
    
     init() {
        self.mContext = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    }

    func add(place: PlaceDescriptionEntity) {
        let entity = NSEntityDescription.entity(forEntityName: "PlaceDescription", in: mContext!)
        let aStud = NSManagedObject(entity: entity!, insertInto: mContext)
        aStud.setValue(place.name, forKey:"name")
        aStud.setValue(place.description, forKey:"place_description")
        aStud.setValue(place.category, forKey:"category")
        aStud.setValue(place.address_title, forKey:"address_title")
        aStud.setValue(place.address_street, forKey:"address_street")
        aStud.setValue(place.elevation, forKey:"elevation")
        aStud.setValue(place.latitude, forKey:"latitude")
        aStud.setValue(place.longitude, forKey:"longitude")
        do{
            try mContext!.save()
            print("Saved \(place.name) to Core Data.")
        } catch let error as NSError{
            print("Error core data adding place \(place.name). Error: \(String(describing:error))")
        }
    }
    
    func get(name: String) -> PlaceDescriptionEntity {
        let ret:PlaceDescriptionEntity = PlaceDescriptionEntity()
        let selectRequest = NSFetchRequest<NSFetchRequestResult>(entityName:"PlaceDescription")
        selectRequest.predicate = NSPredicate(format: "name == %@", name)
        do {
            let results = try mContext!.fetch(selectRequest)
            if results.count > 0 {
                let place = PlaceDescriptionEntity()
                place.name = ((results[0] as AnyObject).value(forKey: "name") as? String)!
                place.category = ((results[0] as AnyObject).value(forKey: "category") as? String)!
                place.description = ((results[0] as AnyObject).value(forKey: "place_description") as? String)!
                place.address_street = ((results[0] as AnyObject).value(forKey: "address_street") as? String)!
                place.address_title = ((results[0] as AnyObject).value(forKey: "address_title") as? String)!
                place.elevation = ((results[0] as AnyObject).value(forKey: "elevation") as? Double)!
                place.latitude = ((results[0] as AnyObject).value(forKey: "latitude") as? Double)!
                place.longitude = ((results[0] as AnyObject).value(forKey: "longitude") as? Double)!
                print ("Retrieved \(name) from Core Data.")
            }
        } catch let error as NSError {
            print("Error in core data get: \(String(describing:error))")
        }
        return ret
    }
    
    func getAll() -> [PlaceDescriptionEntity] {
        let placesFetch = NSFetchRequest<NSFetchRequestResult>(entityName: "PlaceDescription")
        var places:[PlaceDescriptionEntity] = []
        do {
            let results = try mContext!.fetch(placesFetch) as! [PlaceDescription]
            for i in 0...(results.count - 1) {
                let place = PlaceDescriptionEntity()
                print (i)
                place.name = ((results[i] as AnyObject).value(forKey: "name") as? String)!
                place.category = ((results[i] as AnyObject).value(forKey: "category") as? String)!
                place.description = ((results[i] as AnyObject).value(forKey: "place_description") as? String)!
                place.address_street = ((results[i] as AnyObject).value(forKey: "address_street") as? String)!
                place.address_title = ((results[i] as AnyObject).value(forKey: "address_title") as? String)!
                place.elevation = ((results[i] as AnyObject).value(forKey: "elevation") as? Double)!
                place.latitude = ((results[i] as AnyObject).value(forKey: "latitude") as? Double)!
                place.longitude = ((results[i] as AnyObject).value(forKey: "longitude") as? Double)!
                places.append(place)
            }
        } catch let error as NSError {
            print("Error in core data get: \(String(describing:error))")
        }
        return places
    }
    
    func remove(name:String) {
        let selectRequest = NSFetchRequest<NSFetchRequestResult>(entityName: "PlaceDescription")
        selectRequest.predicate = NSPredicate(format: "name == %@",name)
        do{
            let results = try mContext!.fetch(selectRequest)
            if results.count > 0 {
                mContext!.delete(results[0] as! NSManagedObject)
                try mContext?.save()
                print ("Deleted \(name) from Core Data.")
            }
        } catch let error as NSError{
            print("Core data remove of student \(name), encountered error: \(String(describing:error))")
        }
    }
    
    func update(place: PlaceDescriptionEntity) {
        self.remove(name: place.name)
        self.add(place: place)
        print ("Updated \(place.name) in Core Data.")
    }
    
}
