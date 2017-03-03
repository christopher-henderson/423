//
//  PlacesTableViewController.swift
//  assign4
//
//  Created by Christopher Henderson on 3/2/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

//
//  Players.swift
//  assign4
//
//  Created by Christopher Henderson on 2/20/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

import UIKit

class PlacesTableController: UITableViewController {
    
    @IBOutlet weak var delete: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.tableView.reloadData()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let places = self.tabBarController as! PlaceLibrary
        return places.count()
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath)
        -> UITableViewCell {
            let cell = tableView.dequeueReusableCell(withIdentifier: "PlaceCell", for: indexPath)
            let places = self.tabBarController as! PlaceLibrary
            let place = places.get(index: indexPath.row)
            cell.textLabel?.text = place.name
            cell.detailTextLabel?.text = place.description
            return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let controller:PlaceLibrary = self.tabBarController as! PlaceLibrary
        controller.selected = indexPath.row
    }
    
    @IBAction func deletePlace(_ sender: Any) {
        let controller:PlaceLibrary = self.tabBarController as! PlaceLibrary
        if controller.count() > 0 {
            controller.deletePlace(index: controller.selected)
            self.tableView.reloadData()
        }
    }
    
}
