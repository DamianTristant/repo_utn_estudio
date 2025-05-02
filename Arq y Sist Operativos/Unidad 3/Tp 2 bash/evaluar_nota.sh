#!/bin/bash

read -p "Ingrese una nota del (0 a 10): " nota


if [ "$nota" -ge 0 ] && [ "$nota" -le 10 ]; then
  if [ "$nota" -lt 6 ]; then
    echo "Reprobado"
  elif [ "$nota" -le 8 ]; then
    echo "Aprobado"
  else
    echo "Excelente"
  fi
else
  echo "Nota inválida. Ingresá un número del 0 al 10."
fi



