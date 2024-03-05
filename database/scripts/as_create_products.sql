/***********************
* ENVIRONMENT: PRODUCTS
* CREATE TABLES 
***********************/

CREATE TABLE products (
	id SERIAL NOT NULL,
	name VARCHAR(128) NOT NULL,
	description VARCHAR(256) NULL,
	sku VARCHAR(256) NULL,
	um_id INTEGER NOT NULL,
	group_id INTEGER NOT NULL,
	active BOOLEAN NOT NULL DEFAULT TRUE,
	notes TEXT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE supplies (
	id SERIAL NOT NULL,
	name VARCHAR(128) NOT NULL,
	description TEXT NULL,
	um_id INTEGER NOT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE unit_measure (
	id SMALLSERIAL NOT NULL,
	name VARCHAR(256) NOT NULL UNIQUE,
	description TEXT NULL,
	symbol VARCHAR(10) NOT NULL UNIQUE,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE products_images (
	id SERIAL NOT NULL,
	name VARCHAR(256) NOT NULL UNIQUE,
	path VARCHAR(256) NOT NULL,
	product_id INTEGER NOT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE products_groups (
	id SERIAL NOT NULL,
	name VARCHAR(256) NOT NULL UNIQUE,
	description VARCHAR(256) NOT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE products_supplies (
	id SERIAL NOT NULL,
	product_id INTEGER NOT NULL,
	supply_id INTEGER NOT NULL,
	um_id INTEGER NOT NULL,
	amount REAL NOT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS INTERNAL, VIEW DIAGRAM
ALTER TABLE products
	ADD CONSTRAINT fk_um_id
		FOREIGN KEY(um_id)
			REFERENCES unit_measure(id);
			
-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS INTERNAL, VIEW DIAGRAM
ALTER TABLE products
	ADD CONSTRAINT fk_group_id
		FOREIGN KEY(group_id)
			REFERENCES products_groups(id);

-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS EXTERNAL, VIEW DIAGRAM
ALTER TABLE products_images
	ADD CONSTRAINT fk_product_id
		FOREIGN KEY(product_id)
			REFERENCES products(id);
			
-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS EXTERNAL, VIEW DIAGRAM
ALTER TABLE supplies
	ADD CONSTRAINT fk_um_id
		FOREIGN KEY(um_id)
			REFERENCES unit_measure(id);
			
-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS EXTERNAL, VIEW DIAGRAM
ALTER TABLE products_supplies
	ADD CONSTRAINT fk_product_id
		FOREIGN KEY(product_id)
			REFERENCES products(id);
			
-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS EXTERNAL, VIEW DIAGRAM
ALTER TABLE products_supplies
	ADD CONSTRAINT fk_supply_id
		FOREIGN KEY(supply_id)
			REFERENCES supplies(id);
			
-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS EXTERNAL, VIEW DIAGRAM
ALTER TABLE products_supplies
	ADD CONSTRAINT fk_um_id
		FOREIGN KEY(um_id)
			REFERENCES unit_measure(id);


-- INSERT INTO: DEPENDENCES TABLE
INSERT INTO products_groups(
	name,
	description
) VALUES (
	'Frios',
	'Productos frios'
), VALUES (
	'Frios',
	'Productos frios'
),
;
