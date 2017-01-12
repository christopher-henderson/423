//
//  ViewController.swift
//  chris
//
//  Created by Christopher Henderson on 1/11/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var bob: UILabel!
    
    @IBAction func clickMe(_ sender: Any) {
        bob.text = "WHOA YOU CLICKED IT"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bob.text = "hur dur"
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

