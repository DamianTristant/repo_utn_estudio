USE hospital;

-- CASO 1: VALIDOS
-- Insertamos grupos sanguineos validos
INSERT INTO grupo_sanguineo (descripcion) VALUES
('A+') , ('A-') ,
('B+') , ('B-') ,
('AB+'), ('AB-'),
('O+') , ('O-') ;

-- Inserto una historia clinica valida
INSERT INTO historia_clinica (nro_historia, id_grupo_sanguineo, antecedentes, medicacion_actual, observaciones)
VALUES ('HC-2024-0001', 1, 'Ninguno', 'Ninguna', 'Ninguna'),
       ('HC-2024-0002', 4, 'Diabetes controlada', 'Metformina', 'Ninguna');

-- Inserto un paciente valido
INSERT INTO paciente (nombre, apellido, dni, fecha_nacimiento, id_historia)
VALUES ('Juan', 'Perez', '12345678', '1980-05-15', 1),
       ('Maria', 'Gomez', '87654321', '1990-10-20', 2);

-- CASO 2: INVALIDOS

INSERT INTO historia_clinica (nro_historia, id_grupo_sanguineo, antecedentes, medicacion_actual, observaciones)
VALUES ('HC-2024-0003', 5, 'Ninguno', 'Ninguna', 'Ninguna');

-- INTENTO INSERTAR UN PACIENTE CON MISMO DNI:
INSERT INTO paciente (nombre, apellido, dni, fecha_nacimiento, id_historia)
VALUES ('Juan', 'Perez', '12345678', '1980-05-15', 3);

-- INTENTO INSERTAR UNA FECHA DE NACIMIENTO ANTERIOR A 1900-01-01 Y ADEMAS LE ASIGNO UNA HISTORIA CLINICA QUE YA ESTA ASIGNADA A OTRO PACIENTE:
INSERT INTO paciente (nombre, apellido, dni, fecha_nacimiento, id_historia)
VALUES ('Ana', 'Lopez', '11223344', '1899-09-28', 2);