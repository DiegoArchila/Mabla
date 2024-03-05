/******************************************
* SCRIPT CREATION USERS                   *
* VERSION:0.1
* AUTOR: Diego Alonso Archila
* ENVIRONMENT: DEV
*******************************************/

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