//
//  APIBindings.swift
//  assign6
//
//  Created by Christopher Henderson on 4/3/17.
//  Copyright © 2017 Christopher Henderson. All rights reserved.
//

import Foundation


class APIBindings {
    
    static var endpoint = APIBindings.getURL()

    class func getURL() -> String {
        if let infoPlist = Bundle.main.infoDictionary {
            return ((infoPlist["ServerURLString"]) as?  String!)!
        } else {
            NSLog("error getting urlString from info.plist")
            return ""
        }
    }

    class func getPlaces(completion: @escaping ([PlaceDescription]) -> Void, done: @escaping () -> Void) {
        APIBindings.getNames(completion: {(data: String, _: String?) -> Void in
            var place: PlaceDescription
            var places = [PlaceDescription]()
            do {
                let json = data.data(using: String.Encoding.utf8)
                let dict = try JSONSerialization.jsonObject(with: json!,options:.mutableContainers) as?[String:AnyObject]
                for name in dict!["result"]! as! [String] {
                    let resp = APIBindings.getPlace(name: name)
                    print(resp)
                    let json = resp.data(using: String.Encoding.utf8)
                    let dict = try JSONSerialization.jsonObject(with: json!,options:.mutableContainers) as? [String:Any]
                    let obj = dict!["result"] as! [String:Any]
                    place = PlaceDescription.fromJSON(name: name, from: obj)
                    print(place)
                    places.append(place)
                }
            } catch {
                
            }
            completion(places)
            print("Finishing")
            done()
        })
    }
    
    class func getPlace(name: String) -> String {
        let data = "{ \"jsonrpc\": \"2.0\", \"method\": \"get\", \"params\": [\"\(name)\"], \"id\": 3}".data(using: .utf8)!
        return syncHttpPostJSON(url: APIBindings.endpoint, data: data)
    }
    
    class func getNames(completion: @escaping (String, String?) -> Void) {
        let data = "{ \"jsonrpc\": \"2.0\", \"method\": \"getNames\", \"params\": [ ], \"id\": 3}".data(using: .utf8)!
        asyncHttpPostJSON(url: APIBindings.endpoint, data: data, completion: completion)
    };
    
    class func addPlace(place: PlaceDescription, completion: @escaping () -> Void) {
        let data = "{ \"jsonrpc\": \"2.0\", \"method\": \"add\", \"params\": [{\"address-title\":\"\(place.address_title)\",\"address-street\":\"\(place.address_street)\",\"elevation\":\(place.elevation),\"image\":\"asupoly\",\"latitude\":\(place.latitude),\"longitude\":\(place.longitude),\"name\":\"\(place.name)\",\"description\":\"\(place.description)\",\"category\":\"\(place.description)\"}], \"id\": 3}".data(using: .utf8)!
        asyncHttpPostJSON(url: APIBindings.endpoint, data: data, completion: completion)
    }
    
    class func deletePlace(name: String, completion: @escaping () -> Void) {
        let data = "{ \"jsonrpc\": \"2.0\", \"method\": \"remove\", \"params\": [\"\(name)\"], \"id\": 3}".data(using: .utf8)!
        asyncHttpPostJSON(url: APIBindings.endpoint, data: data, completion: completion)
    }
    
    class func syncHttpPostJSON(url: String, data: Data) -> String {
        let request = NSMutableURLRequest(url: NSURL(string: url)! as URL)
        request.httpMethod = "POST"
        request.addValue("application/json",forHTTPHeaderField: "Content-Type")
        request.addValue("application/json",forHTTPHeaderField: "Accept")
        request.httpBody = data
        
        let semaphore = DispatchSemaphore(value: 0)
        var respData: NSData = NSData()
        try! URLSession.shared.dataTask(with: request as URLRequest) { (responseData, _, _) -> Void in
            respData = responseData! as NSData //treat optionals properly
            semaphore.signal()
            }.resume()
        semaphore.wait()
        return NSString(data: respData as Data, encoding: String.Encoding.utf8.rawValue)! as String
    }
    
    // used by methods below to send a request asynchronously.
    // asyncHttpPostJson creates and posts a URLRequest that attaches a JSONRPC request as a Data object
    class func asyncHttpPostJSON(url: String,  data: Data,
                                 completion: @escaping () -> Void) {
        
        let request = NSMutableURLRequest(url: NSURL(string: url)! as URL)
        request.httpMethod = "POST"
        request.addValue("application/json",forHTTPHeaderField: "Content-Type")
        request.addValue("application/json",forHTTPHeaderField: "Accept")
        request.httpBody = data
        HTTPsendRequest(request: request, callback: completion)
    }
    
    // used by methods below to send a request asynchronously.
    // asyncHttpPostJson creates and posts a URLRequest that attaches a JSONRPC request as a Data object
    class func asyncHttpPostJSON(url: String,  data: Data,
                       completion: @escaping (String, String?) -> Void) {
    
        let request = NSMutableURLRequest(url: NSURL(string: url)! as URL)
        request.httpMethod = "POST"
        request.addValue("application/json",forHTTPHeaderField: "Content-Type")
        request.addValue("application/json",forHTTPHeaderField: "Accept")
        request.httpBody = data
        HTTPsendRequest(request: request, callback: completion)
    }
    
    // sendHttpRequest
    class func HTTPsendRequest(request: NSMutableURLRequest,
                         callback: @escaping (String, String?) -> Void) {
        // task.resume() below, causes the shared session http request to be posted in the background
        // (independent of the UI Thread)
        // the use of the DispatchQueue.main.async causes the callback to occur on the main queue --
        // where the UI can be altered, and it occurs after the result of the post is received.
        let task = URLSession.shared.dataTask(with: request as URLRequest) {
            (data, response, error) -> Void in
            if (error != nil) {
                callback("", error!.localizedDescription)
            } else {
                DispatchQueue.main.async(execute: {callback(NSString(data: data!,
                                                                     encoding: String.Encoding.utf8.rawValue)! as String, nil)})
            }
        }
        task.resume()
    }
    
    // sendHttpRequest
    class func HTTPsendRequest(request: NSMutableURLRequest,
                               callback: @escaping () -> Void) {
        // task.resume() below, causes the shared session http request to be posted in the background
        // (independent of the UI Thread)
        // the use of the DispatchQueue.main.async causes the callback to occur on the main queue --
        // where the UI can be altered, and it occurs after the result of the post is received.
        let task = URLSession.shared.dataTask(with: request as URLRequest) {
            (data, response, error) -> Void in
            if (error != nil) {
                print(error)
                callback()
            } else {
                DispatchQueue.main.async(execute: {callback()})
            }
        }
        task.resume()
    }
}
