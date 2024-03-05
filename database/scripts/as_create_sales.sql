/***********************
* ENVIRONMENT: SALES
* CREATE TABLES 
***********************/

CREATE TABLE sales (
	id SERIAL NOT NULL,
	people_id INTEGER NOT NULL,
	location_id INTEGER NOT NULL,
	sale_number VARCHAR(128) NOT NULL,
	sale_total MONEY NOT NULL,
	is_paid BOOLEAN NOT NULL DEFAULT true,
	paid_method_id SMALLINT NOT NULL, 
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id, location_id, created_at)
);

CREATE TABLE paid_methods (
	id SMALLSERIAL NOT NULL,
	paid_method VARCHAR(256) NOT NULL UNIQUE,
	description VARCHAR(256) NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

CREATE TABLE sales_products (
	id SERIAL NOT NULL,
	sale_id INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
	price MONEY NOT NULL,
	amount REAL NOT NULL,
	created_at TIMESTAMP WITH time zone NOT NULL DEFAULT current_timestamp,
	updated_at TIMESTAMP WITH time zone NULL,
	deleted_at TIMESTAMP WITH time zone NULL,
	PRIMARY KEY (id)
);

-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS INTERNAL, VIEW DIAGRAM
ALTER TABLE sales
	ADD CONSTRAINT fk_paid_method_id
		FOREIGN KEY(paid_method_id)
			REFERENCES paid_methods(id);

-- ALTER TABLE: ADD CONSTRAINTS FOREIGN KEYS EXTERNAL, VIEW DIAGRAM
ALTER TABLE sales_products
	ADD CONSTRAINT fk_sale_id
		FOREIGN KEY(sale_id)
			REFERENCES sales(id);