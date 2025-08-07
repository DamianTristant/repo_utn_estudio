#!/bin/bash

read -p "Ingresá tu nombre: " nombre
read -p "Ingresá tu apellido: " apellido

nombre_mayus=$(echo "$nombre" | tr '[:lower:]' '[:upper:]')
apellido_mayus=$(echo "$apellido" | tr '[:lower:]' '[:upper:]')

echo "Tu nombre en mayúsculas es: $nombre_mayus $apellido_mayus"
