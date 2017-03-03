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

}
