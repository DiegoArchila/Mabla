/***********************
* ENVIRONMENT: EMPLOYEES
* CREATE TABLES 
***********************/

CREATE TABLE employees (
	id SERIAL NOT NULL,
	people_id INTEGER NOT NULL,
	location_id INTEGER NOT NULL,
	manager_id INTEGER NULL,
	gender_id INTEGER NOT NULL,
	position_id INTEGER NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NULL,
	phone VARCHAR(32) NOT NULL,
	email VARCHAR(256) NOT NULL,
	active boolean NOT NULL DEFAULT true,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE employees_positions (
	id SMALLSERIAL NOT NULL,
	name_position VARCHAR(256) NOT NULL UNIQUE,
	description VARCHAR(256) NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);
	
-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS INTERNAL, VIEW DIAGRAM
ALTER TABLE employees
	ADD CONSTRAINT fk_position_id
		FOREIGN KEY(position_id)
			REFERENCES employees_positions(id);

-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS EXTERNAL, VIEW DIAGRAM
ALTER TABLE employees
	ADD CONSTRAINT fk_people_id
		FOREIGN KEY(people_id)
			REFERENCES peoples(id);