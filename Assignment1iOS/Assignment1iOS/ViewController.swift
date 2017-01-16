//
//  ViewController.swift
//  Assignment1iOS
//
//  Created by Christopher Henderson on 1/16/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

import UIKit
import Foundation

class ViewController: UIViewController {

    
    @IBOutlet weak var placeDescription: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        placeDescription.text = PlaceDescription().toJSON()
        placeDescription.numberOfLines = 0
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

class PlaceDescription {
    var name = "John Doe"
    var description = "No description available."
    var category = "N/A"
    var address_title = "N/A"
    var address_street = "N/A"
    var elevation = 0
    var latitude = 0
    var longitude = 0
    
    func toJSON() -> String {
        let data: NSDictionary = [
            "name": self.name,
            "description": self.description,
            "category": self.category,
            "address-title": self.address_title,
            "address-street": self.address_street,
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
}
