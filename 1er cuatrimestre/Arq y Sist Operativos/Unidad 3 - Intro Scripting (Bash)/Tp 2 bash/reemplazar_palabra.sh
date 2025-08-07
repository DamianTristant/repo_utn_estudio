#!/bin/bash

texto="Hubo un error en el sistema. Este error debe ser corregido."

nuevo_texto="${texto//error/problemita}"

echo "Texto original: $texto"
echo "Texto modificado: $nuevo_texto"
