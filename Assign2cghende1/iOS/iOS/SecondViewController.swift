//
//  SecondViewController.swift
//  iOS
//
//  Created by Christopher Henderson on 1/23/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // Selected the button in the first ViewController to come to this ViewController.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NSLog("In second ViewController viewWillAppear")
    }
    
    // Selected the button in the first ViewController to come to this ViewController.
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NSLog("In second ViewController viewDidAppear")
    }
    
    // Pressed the button that moves me to the primary ViewController.
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NSLog("In second ViewController viewWillDisappear")
    }
    
    // Pressed the button that moves me to the primary ViewController.
    override func viewDidDisappear(_ animated: Bool){
        super.viewDidDisappear(animated)
        NSLog("In second ViewController viewDidDisappear")
    }

}
