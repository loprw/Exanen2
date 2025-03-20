CREATE TABLE TB_Productos (
id NUMBER(5,0),
nombre VARCHAR2(50) NOT NULL,
tipo VARCHAR2(13) NOT NULL CHECK (tipo IN('Alimentación', 'Electrónica', 'Ropa')),
precio NUMBER(7,2) NOT NULL,
stock NUMBER(5,0) NOT NULL,
CONSTRAINT pk_productos PRIMARY KEY (id)
);

INSERT INTO TB_Productos VALUES (1, 'Espaguetis frescos', 'Alimentación', 0.79, 20);
INSERT INTO TB_Productos VALUES (2, 'Postre de limón', 'Alimentación', 3.54, 5);
INSERT INTO TB_Productos VALUES (3, 'Báscula electrónica', 'Electrónica', 17.35, 2);
INSERT INTO TB_Productos VALUES (4, 'Gorrito navideño', 'Ropa', 2.91, 0);
INSERT INTO TB_Productos VALUES (5, 'Zumo de naranaja', 'Alimentación', 0.84, 9);
INSERT INTO TB_Productos VALUES (6, 'Termómetro electrónico', 'Electrónica', 4.79, 3);
INSERT INTO TB_Productos VALUES (7, 'Par de calcetines', 'Ropa', 2.00, 14);
INSERT INTO TB_Productos VALUES (8, 'Carne picada de ternera', 'Alimentación', 8.01, 0);
INSERT INTO TB_Productos VALUES (9, 'Reloj digital', 'Electrónica', 12.99, 6);
INSERT INTO TB_Productos VALUES (10, 'Pizza precocinada', 'Alimentación', 2.91, 2);
INSERT INTO TB_Productos VALUES (11, 'Despertador', 'Electrónica', 4.79, 1);
INSERT INTO TB_Productos VALUES (12, 'Pañuelo', 'Ropa', 0.89, 7);

SELECT id, nombre, tipo, precio, stock FROM TB_Productos;

SELECT tipo, COUNT(nombre) AS CANTIDAD, ROUND(AVG(precio), 2) AS PRECIOMEDIO FROM TB_Productos GROUP BY tipo;