USE hospital;

-- SE REALIZA UNA CONSULTA DE TODOS LOS DATOS DEL PACIENTE Y LA HISTORIA CLINICA (INNER JOIN).
SELECT p.dni, p.nombre, p.apellido, p.fecha_nacimiento, h.nro_historia, g.descripcion, h.antecedentes, h.medicacion_actual, h.observaciones
FROM paciente p
INNER JOIN historia_clinica h ON p.id = h.id AND p.dni = 30008084
INNER JOIN grupo_sanguineo g ON h.id_grupo_sanguineo = g.id;

-- SE REALIZA UNA CONSULTA DE LOS PACIENTES QUE TIENEN UN TIPO DE SANGRE.
SELECT p.dni, p.nombre, p.apellido, g.descripcion
FROM paciente p
INNER JOIN historia_clinica h ON p.id = h.id
INNER JOIN grupo_sanguineo g ON g.id = h.id_grupo_sanguineo AND g.descripcion = 'A+';

-- SE MUESTAN LOS GRUPOS SANGUINEOS QUE TIENEN MAS DE 100 PACIENTES REGISTRADOS (GROUP BY + HAVING).
SELECT
    g.descripcion AS grupo_sanguineo,
    COUNT(p.id) AS cantidad_pacientes
FROM paciente p
JOIN historia_clinica h ON p.id_historia = h.id
JOIN grupo_sanguineo g ON h.id_grupo_sanguineo = g.id
GROUP BY g.descripcion
HAVING COUNT(p.id) > 1200;

-- SUBCONSULTA, SE CONSULTA QUE PACIENTES TIENEN ANTECEDENTES DE DIABETES.
SELECT nombre, apellido
FROM paciente
WHERE id_historia IN(
	SELECT id
    FROM historia_clinica
    WHERE antecedentes LIKE '%Antecedente Generico 300%'
    );

-- VISTA UTIL
CREATE VIEW vista_ficha_paciente AS
SELECT 
    p.id AS id_paciente,
    p.nombre,
    p.apellido,
    p.dni,
    p.fecha_nacimiento,
    hc.nro_historia,
    hc.antecedentes,
    hc.medicacion_actual,
    hc.observaciones,
    gs.descripcion AS grupo_sanguineo
FROM paciente p
JOIN historia_clinica hc ON p.id_historia = hc.id
JOIN grupo_sanguineo gs ON hc.id_grupo_sanguineo = gs.id
WHERE p.eliminado = 0 AND hc.eliminado = 0;




