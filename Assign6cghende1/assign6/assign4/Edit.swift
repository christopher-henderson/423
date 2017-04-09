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

import UIKit

class Edit: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

    @IBOutlet weak var save: UIButton!
    @IBOutlet weak var picker: UIPickerView!
    @IBOutlet weak var address_street: UITextField!
    @IBOutlet weak var longitude: UITextField!
    @IBOutlet weak var latitude: UITextField!
    @IBOutlet weak var elevation: UITextField!
    @IBOutlet weak var address_title: UITextField!
    @IBOutlet weak var placeDescription: UITextField!
    @IBOutlet weak var name: UITextField!
    
    var pickerData: [String] = ["Hike", "Travel", "Home", "School", "Business", "Government"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let controller:PlaceLibrary = self.tabBarController as! PlaceLibrary
        let place = controller.get(index: controller.selected)
        self.name.text = place.name
        self.latitude.text = String(place.latitude)
        self.longitude.text = String(place.longitude)
        self.elevation.text = String(place.elevation)
        self.address_street.text = place.address_street
        self.address_title.text = place.address_title
        self.placeDescription.text = place.description
        self.picker.dataSource = self;
        self.picker.delegate = self;
        for (index, category) in self.pickerData.enumerated() {
            if place.category == category {
                self.picker.selectRow(index, inComponent: 0, animated: true)
            }
        }
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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

    @IBAction func save(_ sender: Any) {
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
        let place = controller.get(index: controller.selected)
        place.name = self.name.text!
        place.description = self.placeDescription.text!
        place.category = category
        place.address_title = self.address_title.text!
        place.address_street = self.address_street.text!
        place.elevation = elevation!
        place.latitude = latitude!
        place.longitude = longitude!
        self.tabBarController?.selectedIndex = 0
        APIBindings.addPlace(place: place, completion: controller.load)
    }

}
