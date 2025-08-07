#!/bin/bash

contraseña=""

until [ "$contraseña" = "secreto" ]
do
  read -p "Ingresá la contraseña: " contraseña
done

echo "Contraseña correcta."


