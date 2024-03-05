/***********************
** CREATE TRIGGERS
** VERSION: 0.1
***********************/

/**
	MAIN FUNCTION
*/
CREATE OR REPLACE FUNCTION fn_on_tables() RETURNS TRIGGER
AS $$
BEGIN
	CASE TG_OP
		WHEN 'UPDATE' THEN
			IF (OLD IS DISTINCT FROM NEW) THEN
				NEW.updated_at=NOW();
				RAISE NOTICE 'Updated on % SUCCESSFUL', TG_TABLE_NAME;
				RETURN NEW;
			ELSE
				RAISE NOTICE 'Nothing to update on %', TG_TABLE_NAME;
				RETURN NULL;
			END IF;
		WHEN 'DELETE' THEN
			IF TG_TABLE_NAME='products' THEN
				EXECUTE 'UPDATE '|| TG_TABLE_NAME ||' SET deleted_at=NOW(), active=false WHERE id=' || OLD.id;
			ELSE
				EXECUTE 'UPDATE '|| TG_TABLE_NAME ||' SET deleted_at=NOW() WHERE id=' || OLD.id;
			END IF;	
			RETURN NULL;
	END CASE;		
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------------------------------------


/**
	TRIGGER: peoples
*/
CREATE OR REPLACE TRIGGER TG_on_update_peoples
BEFORE UPDATE OR DELETE ON peoples
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: peoples_dni_types
*/
CREATE TRIGGER TG_on_update_peoples_dni_types
BEFORE UPDATE OR DELETE ON peoples_dni_types
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: peoples_genders
*/
CREATE TRIGGER TG_on_update_peoples_genders
BEFORE UPDATE OR DELETE ON peoples_genders
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: peoples_locations
*/
CREATE TRIGGER TG_on_update_peoples_locations
BEFORE UPDATE OR DELETE ON peoples_locations
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

-------------------------------------------------------------------------------

/**
	TRIGGER: products
*/
CREATE TRIGGER TG_on_update_products
BEFORE UPDATE OR DELETE ON products
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: supplies
*/
CREATE TRIGGER TG_on_update_supplies
BEFORE UPDATE OR DELETE ON supplies
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: unit_measure
*/
CREATE TRIGGER TG_on_update_unit_measure
BEFORE UPDATE OR DELETE ON unit_measure
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: products_images
*/
CREATE TRIGGER TG_on_update_products_images
BEFORE UPDATE OR DELETE ON products_images
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: products_groups
*/
CREATE TRIGGER TG_on_update_products_groups
BEFORE UPDATE OR DELETE ON products_groups
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: products_supplies
*/
CREATE TRIGGER TG_on_update_products_supplies
BEFORE UPDATE OR DELETE ON products_supplies
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

-------------------------------------------------------------------------------

/**
	TRIGGER: employees
*/
CREATE TRIGGER TG_on_update_employees
BEFORE UPDATE OR DELETE ON employees
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables(); 

/**
	TRIGGER: employees_positions
*/
CREATE TRIGGER TG_on_update_employees_positions
BEFORE UPDATE OR DELETE ON employees_positions
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables(); 

-------------------------------------------------------------------------------

/**
	TRIGGER: locations
*/
CREATE TRIGGER TG_on_update_locations
BEFORE UPDATE OR DELETE ON locations
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables(); 

/**
	TRIGGER: locations_types
*/
CREATE TRIGGER TG_on_update_locations_types
BEFORE UPDATE OR DELETE ON locations_types
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: locations_phones
*/
CREATE TRIGGER TG_on_update_locations_phones
BEFORE UPDATE OR DELETE ON locations_phones
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: locations_emails
*/
CREATE TRIGGER TG_on_update_locations_emails
BEFORE UPDATE OR DELETE ON locations_emails
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: locations_images
*/
CREATE TRIGGER TG_on_update_locations_images
BEFORE UPDATE OR DELETE ON locations_images
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

-------------------------------------------------------------------------------

/**
	TRIGGER: sales
*/
CREATE TRIGGER TG_on_update_sales
BEFORE UPDATE OR DELETE ON sales
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: sales_products
*/
CREATE TRIGGER TG_on_update_sales_products
BEFORE UPDATE OR DELETE ON sales_products
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();

/**
	TRIGGER: paid_methods
*/
CREATE TRIGGER TG_on_update_paid_methods
BEFORE UPDATE OR DELETE ON paid_methods
FOR EACH ROW
EXECUTE PROCEDURE fn_on_tables();