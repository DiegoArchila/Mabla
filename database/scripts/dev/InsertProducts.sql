/******************************************
* SCRIPT CREATION PRODUCTS                *
* VERSION:0.1
* AUTOR: Diego Alonso Archila
* ENVIRONMENT: DEV
*******************************************/

BEGIN;

/**
* ids:
* Unidad: 1
*/
INSERT INTO unit_measure (
    name,
    description,
    symbol
) VALUES (
    'Unidad',
    'Una unidad',
    'UND'
);

END;

BEGIN;
/**
* ids:
* Caliente: 1,
* Frios: 2,
* Reposteria.
*/
INSERT INTO products_groups (
    name,
	description
) VALUES (
    'Caliente',
    'Todo lo relacionado con productos calientes'
), (
    'Frios',
    'Todo lo relacionado con productos frios'
), (
    'Reposteria',
    'Todo lo relacionado con a la reposteria.'
);

END;

BEGIN;
INSERT INTO products (
    name,
    description,
    um_id,
    group_id
) VALUES (
    'Cafe tradicional * 3 ONZ',
    null,
    1,
    1
), (
    'Cafe tradicional * 5 ONZ',
    null,
    1,
    1
), (
    'Cafe tradicional * 7 ONZ',
    null,
    1,
    1
), (
    'Malteada mocachinno * 7 ONZ',
    null,
    1,
    2
), (
    'Malteada mocachinno * 9 ONZ',
    null,
    1,
    2
), (
    'Malteada mocachinno * 12 ONZ',
    null,
    1,
    2
), (
    'Torta rebelve porcion',
    null,
    1,
    3
), (
    'Postre 3 leches * 8 ONZ',
    null,
    1,
    3
), (
    'Torta rebelve * 1 Kilo',
    null,
    1,
    3
);

END;