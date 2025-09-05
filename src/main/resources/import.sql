INSERT INTO owner (id, name, email) VALUES (1, 'Ana Pop', 'ana.pop@example.com');
INSERT INTO owner (id, name, email) VALUES (2, 'Bogdan Ionescu', 'bogdan.ionescu@example.com');
INSERT INTO owner (id, name, email) VALUES (3, 'Ion Ionel', 'ion.ionel@example.com');
INSERT INTO owner (id, name, email) VALUES (4, 'Andrei Antonescu', 'andrei.antonescu@example.com');
INSERT INTO owner (id, name, email) VALUES (5, 'Toma Alexandru', 'toma.alexandru@example.com');
INSERT INTO owner (id, name, email) VALUES (6, 'Ionescu Maria', 'maria.ionescu@example.com');
INSERT INTO owner (id, name, email) VALUES (7, 'Popescu Andrei', 'andrei.popescu@example.com');

INSERT INTO car (id, vin, make, model, year_of_manufacture, owner_id) VALUES (1, 'VIN12345', 'Dacia', 'Logan', 2018, 1);
INSERT INTO car (id, vin, make, model, year_of_manufacture, owner_id) VALUES (2, 'VIN67890', 'VW', 'Golf', 2021, 2);
INSERT INTO car (id, vin, make, model, year_of_manufacture, owner_id) VALUES (3, 'VIN23232', 'Renault', 'Megane', 2010, 3);
INSERT INTO car (id, vin, make, model, year_of_manufacture, owner_id) VALUES (4, 'VIN01010', 'Tesla', 'Model3', 2020, 4);
INSERT INTO car (id, vin, make, model, year_of_manufacture, owner_id) VALUES (5, 'VIN00000', 'BMW', 'M5', 2023, 5);
INSERT INTO car (id, vin, make, model, year_of_manufacture, owner_id) VALUES (6, 'VIN00001', 'Audi', 'A4', 2022, 6);
INSERT INTO car (id, vin, make, model, year_of_manufacture, owner_id) VALUES (7, 'VIN00002', 'Mercedes', 'C200', 2021, 7);

INSERT INTO insurancepolicy (id, car_id, provider, start_date, end_date) VALUES (1, 1, 'Allianz', DATE '2024-01-01', DATE '2024-12-31');
INSERT INTO insurancepolicy (id, car_id, provider, start_date, end_date) VALUES (2, 1, 'Groupama', DATE '2025-01-01', NULL);
INSERT INTO insurancepolicy (id, car_id, provider, start_date, end_date) VALUES (3, 2, 'Allianz', DATE '2025-03-01', DATE '2025-09-30');
