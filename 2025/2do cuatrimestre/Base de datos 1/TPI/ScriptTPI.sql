CREATE DATABASE IF NOT EXISTS hospital;
USE hospital;

DROP TABLE IF EXISTS paciente;         -- A
DROP TABLE IF EXISTS historia_clinica; -- B
DROP TABLE IF EXISTS grupo_sanguineo;

CREATE TABLE grupo_sanguineo(
   id          INT        PRIMARY KEY AUTO_INCREMENT,
   descripcion VARCHAR(5) NOT NULL UNIQUE
);

CREATE TABLE historia_clinica(
   id                 BIGINT      PRIMARY KEY AUTO_INCREMENT,
   eliminado          TINYINT     DEFAULT 0                 ,
   nro_historia       VARCHAR(20) NOT NULL UNIQUE           ,
   id_grupo_sanguineo INT         NOT NULL                  ,
   antecedentes       TEXT                                  ,
   medicacion_actual  TEXT                                  ,
   observaciones      TEXT                                  ,
   FOREIGN KEY(id_grupo_sanguineo) REFERENCES grupo_sanguineo(id)
);

CREATE TABLE paciente(
   id                  BIGINT       PRIMARY KEY AUTO_INCREMENT,
   eliminado           TINYINT      DEFAULT 0                 ,
   nombre              VARCHAR(80)  NOT NULL                  ,
   apellido            VARCHAR(80)  NOT NULL                  , 
   dni                 VARCHAR(15)  NOT NULL UNIQUE           ,
   fecha_nacimiento    DATE                                   ,
   id_historia         BIGINT       UNIQUE                    ,
   FOREIGN KEY(id_historia) REFERENCES historia_clinica(id)   ,
   
   CHECK (dni != ''),
   CHECK (fecha_nacimiento     >= '1900-01-01')
);