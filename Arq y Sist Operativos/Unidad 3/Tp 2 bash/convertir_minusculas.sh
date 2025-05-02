#!/bin/bash


read -p "Ingresá un texto: " texto


texto_minusculas=$(echo "$texto" | tr '[:upper:]' '[:lower:]')

echo "El texto en minúsculas es: $texto_minusculas"
