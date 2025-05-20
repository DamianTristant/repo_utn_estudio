#!/bin/bash

read -p "Ingrese el nombre del archivo que busca: " archivo

if [ -f "$archivo" ]; then
  echo "El archivo buscado existe."
else
  echo "El archivo buscado  no existe."
fi


