/****************
* CREATE TABLES *
****************/

-- DROP TABLES
DROP TABLE IF EXISTS peoples_locations;
DROP TABLE IF EXISTS peoples;
DROP TABLE IF EXISTS peoples_genders;
DROP TABLE IF EXISTS peoples_dni_types;

-- 1. CREATE TABLE: peoples
CREATE TABLE peoples (
	id SERIAL NOT NULL,
	first_name VARCHAR(256) NOT NULL,
	last_name VARCHAR(256) NOT NULL,
	date_born DATE NOT NULL,
	gender_id INTEGER NOT NULL,
	dni_type_id INTEGER NOT NULL,
	dni VARCHAR(128) NOT NULL,
	phone VARCHAR(32) NOT NULL,
	email VARCHAR(256) NOT NULL,
	image_profile VARCHAR(256) NOT NULL,
	pwd VARCHAR(256) NOT NULL,
	active boolean NOT NULL DEFAULT true,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	update_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

-- 2. CREATE TABLE: peoples_genders
CREATE TABLE peoples_genders (
	id SERIAL NOT NULL,
	gender VARCHAR(64) NOT NULL UNIQUE,
	description VARCHAR(256) NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	update_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

-- 3. CREATE TABLE: peoples_genders
CREATE TABLE peoples_locations (
	id SERIAL NOT NULL,
	name_location VARCHAR(128) NOT NULL,
	people_id INTEGER NOT NULL,
	phone VARCHAR(32) NOT NULL,
	email VARCHAR(256) NOT NULL,
	country VARCHAR(128) NOT NULL,
	state_departament VARCHAR(128) NOT NULL,
	city_town VARCHAR(128) NOT NULL,
	address_line VARCHAR(256) NOT NULL,
	notes TEXT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	update_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

-- 4. CREATE TABLE: peoples_dni_type
CREATE TABLE peoples_dni_types (
	id SERIAL NOT NULL,
	dni_type VARCHAR(128) NOT NULL UNIQUE,
	description VARCHAR(256) NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	update_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

-- 5. ALTER TABLE: ADD CONSTRAINT
ALTER TABLE peoples
	ADD CONSTRAINT fk_gender_id
		FOREIGN KEY(gender_id)
			REFERENCES peoples_genders(id),
	ADD CONSTRAINT fk_dni_type_id
		FOREIGN KEY(dni_type_id)
			REFERENCES peoples_dni_types(id);
			
ALTER TABLE peoples_locations			
	ADD CONSTRAINT fk_peoples_locations_id
		FOREIGN KEY(people_id)
			REFERENCES peoples(id);
			
-- 5. INSERT INTO: DEPENDENCES TABLE
INSERT INTO peoples_genders(
	gender,
	description
) VALUES (
	'Hombre',
	'Genero masculino'
), (
	'Mujer',
	'Genero femenimo'
), (
	'Otro',
	'Otro genero'
);

/*
*https://www.registraduria.gov.co/Glosario-de-identificacion.html
*/
INSERT INTO peoples_dni_types (
	dni_type,
	description
) VALUES (
	'CC',
	'Cedula Ciudadania'
), (
	'TI',
	'Tarjeta de Identidad'
), (
	'PA',
	'Pasaporte'
);

INSERT INTO peoples (
	first_name,
	last_name,
	date_born,
	gender_id,
	dni_type_id,
	dni,
	phone,
	email,
	image_profile,
	pwd,
	active
) VALUES (
	'Diego',
	'Archila',
	'16/01/1991',
	'1',
	'1',
	'1082928069',
	'3142836724',
	'daat3523@gmail.com',
	'/public/img/avatar.jpg',
	'P45W0R7',
	true
);

INSERT INTO peoples_locations (
	name_location,
	people_id,
	phone,
	email,
	country,
	state_departament,
	city_town,
	address_line
) VALUES (
	'mi casa',
	1,
	'3142836724',
	'daat3523@gmail.com',
	'colombia',
	'antioquia',
	'rionegro',
	'calle 52 #61-337. Segundo piso. Vereda falda del palo.'
);