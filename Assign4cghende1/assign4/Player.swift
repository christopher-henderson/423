//
//  Player.swift
//  assign4
//
//  Created by Christopher Henderson on 2/20/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

import Foundation
import UIKit

struct Player {
    var name: String?
    var game: String?
    var rating: Int
    
    init(name: String?, game: String?, rating: Int) {
        self.name = name
        self.game = game
        self.rating = rating
    }
}
