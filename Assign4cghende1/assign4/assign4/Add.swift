//
//  Add.swift
//  assign4
//
//  Created by Christopher Henderson on 3/1/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

import UIKit

class Add: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    @IBOutlet weak var name: UITextField!

    @IBOutlet weak var placeDescription: UITextField!
    @IBOutlet weak var longitude: UITextField!
    @IBOutlet weak var elevation: UITextField!
    @IBOutlet weak var latitude: UITextField!
    @IBOutlet weak var address_street: UITextField!
    @IBOutlet weak var address_title: UITextField!
    @IBOutlet weak var save: UIButton!
    @IBOutlet weak var picker: UIPickerView!
    @IBOutlet weak var label: UILabel!
    
    var pickerData: [String] = ["Hike", "Travel", "Home", "School", "Business", "Government"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        let controller:PlaceLibrary = self.tabBarController as! PlaceLibrary
//        label.text = controller.get(index: 0).name
//        controller.get(index: 0).name = "JOE"
        self.picker.dataSource = self;
        self.picker.delegate = self;
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return self.pickerData.count;
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return self.pickerData[row]
    }
    
    @IBAction func savePlace(_ sender: UIButton) {
        let elevation = Double(self.elevation.text!)
        let latitude = Double(self.latitude.text!)
        let longitude = Double(self.longitude.text!)
        if elevation == nil || latitude == nil || longitude == nil {
            let alert: UIAlertView = UIAlertView()
            alert.delegate = self
            alert.title = "Invalid input."
            alert.message = "Elevation, latitude, and longitude must be numbers."
            alert.addButton(withTitle: "OK")
            alert.show()
            return
        }
        let category = self.pickerData[self.picker.selectedRow(inComponent: 0)]
        let controller:PlaceLibrary = self.tabBarController as! PlaceLibrary
        controller.addPlace(PlaceDescription(name: self.name.text!, description: self.placeDescription.text!, category: category, address_title: self.address_title.text!, address_street: self.address_street.text!, elevation: elevation!, latitude: latitude!, longitude: longitude!))
        self.tabBarController?.selectedIndex = 0
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}