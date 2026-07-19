@echo off
FOR /F "tokens=2 delims==" %%I IN ('wmic os get localdatetime /value') DO SET DATETIME=%%I
SET FECHA_HOY=%DATETIME:~0,4%-%DATETIME:~4,2%-%DATETIME:~6,2%
SET DESTINO=resguardos_tpi\%FECHA_HOY%

IF NOT EXIST "%DESTINO%" (
    mkdir "%DESTINO%"
    echo Carpeta creada: %DESTINO%
)

echo Iniciando backup...

mongodump ^
  --uri="mongodb://ximenasosa:MongoTP72026@ac-ngulq80-shard-00-00.uxlzy5e.mongodb.net:27017,ac-ngulq80-shard-00-01.uxlzy5e.mongodb.net:27017,ac-ngulq80-shard-00-02.uxlzy5e.mongodb.net:27017/?ssl=true&replicaSet=atlas-11wj2q-shard-0&authSource=admin" ^
  --db=tpi_taller_carpinteria ^
  --out="%DESTINO%"

IF %ERRORLEVEL% EQU 0 (
    echo Backup completado en: %DESTINO%
) ELSE (
    echo ERROR: revisar conexion y credenciales.
)
pause