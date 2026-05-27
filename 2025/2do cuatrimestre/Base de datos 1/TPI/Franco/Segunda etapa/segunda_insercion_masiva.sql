-- LIMPIO TABLAS
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE paciente;
TRUNCATE TABLE historia_clinica;
SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS digitos;
DROP TABLE IF EXISTS numeros;

SET @seed = 42;

-- CREAMOS TABLA TEMPORAL DE DIGITOS
CREATE TEMPORARY TABLE digitos (d INT);
INSERT INTO digitos VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9);

-- CREAMOS TABLA TEMPORAL DE NUMEROS
CREATE TEMPORARY TABLE numeros (n INT PRIMARY KEY);

-- LENAMOS CON NUMEROS DE 1 AL 200.000
INSERT INTO numeros (n)
SELECT 
d1.d * 100000 + d2.d * 10000 + d3.d * 1000 + d4.d * 100 + d5.d * 10 + d6.d AS numero
FROM digitos d1
CROSS JOIN digitos d2
CROSS JOIN digitos d3
CROSS JOIN digitos d4
CROSS JOIN digitos d5
CROSS JOIN digitos d6
HAVING numero > 0 AND numero <= 200000;

-- INSERTAMOS PRIMEROS 10.000 REGISTROS EN historia_clinica
INSERT INTO historia_clinica(nro_historia, id_grupo_sanguineo, antecedentes, medicacion_actual, observaciones)
SELECT
CONCAT('HC', LPAD(n, 6, '0'))           AS nro_historia      ,
1 + FLOOR(RAND(@seed := @seed + 1) * 8) AS id_grupo_sanguineo,
CONCAT('Antecedente genérico ', n)      AS antecedentes      ,
CONCAT('Medicación genérica ' , n)      AS medicacion_actual ,
CONCAT('Observación genérica ', n)      AS observaciones
FROM numeros
LIMIT 10000;

-- INSERTAMOS PRIMEROS 10.000 REGISTROS EN paciente
INSERT INTO paciente (nombre, apellido, dni, fecha_nacimiento, id_historia)
SELECT
CASE (n MOD 11)       WHEN 0 THEN 'Juan'      WHEN 1 THEN 'María'    WHEN 2 THEN 'Carlos'
                      WHEN 3 THEN 'Ana'       WHEN 4 THEN 'Franco'   WHEN 5 THEN 'Mateo'
                      WHEN 6 THEN 'Agustina'  WHEN 7 THEN 'Nicolas'  WHEN 8 THEN 'Sofia'
                      WHEN 9 THEN 'Roberto'   WHEN 10 THEN 'Luis'    END AS nombre,
CASE ((n * 7) MOD 11) WHEN 0 THEN 'Rodriguez' WHEN 1 THEN 'Gonzalez' WHEN 2 THEN 'Martinez'
					  WHEN 3 THEN 'Blanco'    WHEN 4 THEN 'Romero'   WHEN 5 THEN 'Herrera'
					  WHEN 6 THEN 'Fernandez' WHEN 7 THEN 'Gomez'    WHEN 8 THEN 'Lopez'
                      WHEN 9 THEN 'Diaz'      WHEN 10 THEN 'Perez'   END AS apellido,
LPAD(30000000 + n, 8, '0')                                                          AS dni,
DATE_SUB(CURDATE(), INTERVAL FLOOR(18*365 + RAND(@seed := @seed + 1) * 62*365) DAY) AS fecha_nacimiento,
n                                                                                   AS id_historia
FROM numeros
LIMIT 10000;