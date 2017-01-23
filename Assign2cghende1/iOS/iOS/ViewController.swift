//
//  ViewController.swift
//  iOS
//
//  Created by Christopher Henderson on 1/23/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var button: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // Begin the application or come back to the this ViewController from the secondary ViewController.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NSLog("In first ViewController viewWillAppear")
    }
    
    // Begin the application or come back to the this ViewController from the secondary ViewController.
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NSLog("In first ViewController viewDidAppear")
    }
    
    // Pressed the button that moves me to the secondary ViewController.
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NSLog("In first ViewController viewWillDisappear")
    }
    
    // Pressed the button that moves me to the secondary ViewController.
    override func viewDidDisappear(_ animated: Bool){
        super.viewDidDisappear(animated)
        NSLog("In first ViewController viewDidDisappear")
    }

}

