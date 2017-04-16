CREATE TABLE `Places` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT,
	`description`	TEXT,
	`category`	TEXT,
	`address_title`	TEXT,
	`address_street`	TEXT,
	`elevation`	REAL,
	`longitude`	REAL,
	`latitude`	REAL,
	`image`	TEXT
);

INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Calgary-Alberta", "The home of the Calgary Stampede Celebration", "Travel", "Calgary International Airport", "2000 Airport Rd NE$Calgary AB T2E 6Z8 Canada", 3556.0, -114.011246, 51.131377, "calgaryalberta");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Notre-Dame-Paris", "The 13th century cathedral with gargoyles, one of the first flying buttress, and home of the purported crown of thorns.", "Travel", "Cathedral Notre Dame de Paris", "6 Parvis Notre-Dame Pl Jean-Paul-II$75004 Paris France", 115.0, 2.34991, 48.852972, "notredame3rdstatuewest");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("UAK-Anchorage", "University of Alaska's largest campus", "School", "University of Alaska at Anchorage", "290 Spirit Dr$Anchorage AK 99508", 0.0, -149.826721, 61.189748, "univalaska");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("London-England", "Renaissance Hotel at the Heathrow Airport", "Travel", "Renaissance London Heathrow Airport", "5 Mondial Way$Harlington Hayes UB3 UK", 82.0, -0.445286, 51.481959, "londonunderground");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Rogers-Trailhead", "Trailhead for hiking to Rogers Canyon Ruins and Reavis Ranch", "Hike", "", "", 4500.0, -111.173393, 33.422212, "rogerstrough");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Muir-Woods", "Redwood forest North of San Francisco, surrounded by Mount Tamalpais State Park.", "Hike", "Muir Woods National Monument", "1 Muir Woods Rd$Mill Valley CA 94941", 350.0, -122.5957, 37.8912, "muirwoods");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Toreros", "The University of San Diego, a private Catholic undergraduate university.", "School", "University of San Diego", "5998 Alcala Park$San Diego CA 92110", 200.0, -117.188204, 32.771923, "univsandiego");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Circlestone", "Indian Ruins located on the second highest peak in the Superstition Wilderness of the Tonto National Forest. Leave Fireline at Turney Spring (33.487610,-111.132400)", "Hike", "", "", 6000.0, -111.134345, 33.477524, "circlestone");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Windcave-Peak", "Beyond the Wind Cave is a half mile trail with 250' additional elevation to the peak overlooking Usery and the Valley.", "Hike", "Usery Mountain Recreation Area", "3939 N Usery Pass Rd$Mesa AZ 85207", 3130.0, -111.595709, 33.476069, "windcavepeak");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("ASU-West", "Home of ASU's Applied Computing Program", "School", "ASU West Campus", "13591 N 47th Ave$Phoenix AZ 85051", 1100.0, -112.159469, 33.608979, "asuwest");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("ASU-Poly", "Home of ASUs Software Engineering Programs", "School", "ASU Software Engineering", "7171 E Sonoran Arroyo Mall$Peralta Hall 230$Mesa AZ 85212", 1300.0, -111.679121, 33.306388, "asupoly");

INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("New-York-NY", "New York City Hall at West end of Brooklyn Bridge", "Travel", "New York City Hall", "1 Elk St$New York NY 10007", 2.0, -74.005948, 40.712991, "newyork");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Moscow-Russia", "The Marriott Courtyard in downtown Moscow", "Travel", "Courtyard Moscow City Center", "Voznesenskiy per 7 $ Moskva Russia 125009", 512.0, 37.604042, 55.758087, "moscow");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Barrow-Alaska", "The only real way in and out of Barrow Alaska.", "Travel", "Will Rogers Airport", "1725 Ahkovak St$Barrow AK 99723", 5.0, -156.779417, 71.287881, "barrowalaska");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Reavis-Grave", "Grave site of Reavis Ranch Proprietor.", "Hike", "", "", 3900.0, -111.182511, 33.441499, "reavisgrave");


INSERT INTO `Places` (name, description, category, address_title, address_street, elevation, longitude, latitude, image) VALUES ("Reavis-Ranch", "Historic Ranch in Superstition Mountains famous for Apple orchards", "Hike", "", "", 5000.0, -111.155385, 33.491154, "reavisranch");
