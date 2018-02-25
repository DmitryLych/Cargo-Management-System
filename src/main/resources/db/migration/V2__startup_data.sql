INSERT INTO users VALUES (1, 'admin', 'admin', TRUE, TRUE, TRUE, TRUE, NULL, NULL, NULL);
INSERT INTO authorities VALUES (1, 'Admin', 1);
INSERT INTO companies VALUES (1, 'Grodno', 1, 'Trucks', 'trucks@gmail.com', '+375(29)1239947');
INSERT INTO users VALUES (4, 'company', 'company', TRUE, TRUE, TRUE, TRUE, 1, NULL, NULL);
INSERT INTO authorities VALUES (4, 'CompanyLead', 4);
INSERT INTO customers VALUES (1, 'Grodno', '555555', 1, 'Boris', 'boris@gmail.com', '+375(29)1234567');
INSERT INTO users VALUES (3, 'customer', 'customer', TRUE, TRUE, TRUE, TRUE, NULL, 1, NULL);
INSERT INTO authorities VALUES (3, 'Customer', 3);
INSERT INTO driver_licenses VALUES (1, 'a,b,c,d,e', 1, NULL, '20120618 10:34:09 AM');
INSERT INTO medical_examinations VALUES (1, 1, '20120618 10:34:09 AM');
INSERT INTO trailers VALUES (1, 'red', 5.5, 12.5, '1234 KH-4', 1, 'ref', 12, 11.1, '20120618 10:34:09 AM');
INSERT INTO trucks VALUES (1, '1235', 'red', '3212 KH-4', 1, 10.0, '20120618 10:34:09 AM', 1);
INSERT INTO drivers VALUES (1, 'Grodno', 1, 'driver@gmail.com', 'Boris', 'Diplomov', FALSE, '+375(29)1233211',
                               '20120618 10:34:09 AM', 1, 1, 1, 1);
INSERT INTO users VALUES (2, 'driver', 'driver', TRUE, TRUE, TRUE, TRUE, NULL, NULL, 1);
INSERT INTO authorities VALUES (2, 'Driver', 2);
INSERT INTO orders VALUES (1, 100.5, FALSE, 'Grodno', TRUE, FALSE, 'Minsk', 1, 1);
INSERT INTO goods VALUES (1, 'apples', 'apples', 222.5, 20.5, 1);
INSERT INTO insurance_policies VALUES (1, 1000.1, 'truck', '20120618 10:34:09 AM', 1);