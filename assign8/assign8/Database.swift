//
//  Database.swift
//  assign8
//
//  Created by Christopher Henderson on 4/24/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

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
            }
        } catch let error as NSError {
            print("Error in core data get: \(String(describing:error))")
        }
        //print("core data get returning \(ret.toJsonString())")
        return ret
    }
    
    func getAll() -> [PlaceDescriptionEntity] {
        let selectRequest = NSFetchRequest<NSFetchRequestResult>(entityName:"PlaceDescription")
        var places:[PlaceDescriptionEntity] = []
        do {
            let results = try mContext!.fetch(selectRequest)
            for i in 0...results.count {
                let place = PlaceDescriptionEntity()
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
        //print("core data get returning \(ret.toJsonString())")
        return places
    }
    
    func remove(name:String){
        let selectRequest = NSFetchRequest<NSFetchRequestResult>(entityName: "PlaceDescription")
        selectRequest.predicate = NSPredicate(format: "name == %@",name)
        do{
            let results = try mContext!.fetch(selectRequest)
            if results.count > 0 {
                mContext!.delete(results[0] as! NSManagedObject)
                try mContext?.save()
            }
        } catch let error as NSError{
            print("Core data remove of student \(name), encountered error: \(String(describing:error))")
        }
    }
    
}
