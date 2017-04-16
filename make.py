#!/usr/bin/env python
import json


# CREATE TABLE `Places` (
# 	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
# 	`name`	TEXT,
# 	`description`	TEXT,
# 	`category`	TEXT,
# 	`address_title`	TEXT,
# 	`address_street`	TEXT,
# 	`elevation`	REAL,
# 	`longitude`	REAL,
# 	`latitude`	REAL,
# 	`image`	BLOB
# );

with open("/Users/chris/Downloads/examples/placeServer/PlaceNRouteJsonRPCServer/places.json", "r") as j:
    rows = json.loads("".join(j.readlines()))

f = """
INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("{name}", "{description}", "{category}", "{address_title}", "{address_street}", {elevation}, {longitude}, {latitude}, "{image}");
"""
# print(rows)
for name, value in rows.items():
    value["address_title"] = value["address-title"]
    value["address_street"] = value["address-street"]
    del value["address-title"]
    del value["address-street"]
    print(f.format(**value))
