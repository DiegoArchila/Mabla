/***********************
* ENVIRONMENT: LOCATIONS
* CREATE TABLES 
***********************/

CREATE TABLE locations (
	id SMALLSERIAL NOT NULL,
	name VARCHAR(128) NOT NULL,
	location_type_id SMALLINT NOT NULL,
	manager_id INTEGER NOT NULL,
	country VARCHAR(128) NOT NULL,
	state_department VARCHAR(128) NOT NULL,
	city_town VARCHAR(128) NOT NULL,
	address_line VARCHAR(256) NOT NULL,
	notes TEXT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE locations_types (
	id SMALLSERIAL NOT NULL,
	name VARCHAR(128) NOT NULL,
	description VARCHAR(256) NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE locations_phones (
	id SERIAL NOT NULL,
	phone VARCHAR(32) NOT NULL,
	description VARCHAR(256) NULL,
	location_id INTEGER NOT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE locations_emails (
	id SERIAL NOT NULL,
	email VARCHAR(256) NOT NULL UNIQUE,
	description VARCHAR(256) NULL,
	location_id INTEGER NOT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE locations_images (
	id SERIAL NOT NULL,
	path VARCHAR(256) NOT NULL,
	name VARCHAR(128) NULL,
	description VARCHAR(256) NULL,
	location_id INTEGER NOT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS INTERNAL, VIEW DIAGRAM
ALTER TABLE locations
	ADD CONSTRAINT fk_location_type_id
		FOREIGN KEY(location_type_id)
			REFERENCES locations_types(id);
			
-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS INTERNAL, VIEW DIAGRAM
ALTER TABLE locations_phones
	ADD CONSTRAINT fk_location_id
		FOREIGN KEY(location_id)
			REFERENCES locations(id);
			
-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS INTERNAL, VIEW DIAGRAM
ALTER TABLE locations_emails
	ADD CONSTRAINT fk_location_id
		FOREIGN KEY(location_id)
			REFERENCES locations(id);
			
-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS INTERNAL, VIEW DIAGRAM
ALTER TABLE locations_images
	ADD CONSTRAINT fk_location_id
		FOREIGN KEY(location_id)
			REFERENCES locations(id);