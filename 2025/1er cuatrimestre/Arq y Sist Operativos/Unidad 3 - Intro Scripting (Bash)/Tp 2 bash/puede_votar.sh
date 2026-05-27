#!/bin/bash

read -p "Ingresá tu nombre: " nombre
read -p "Ingresá tu edad: " edad

if [ "$edad" -ge 16 ]; then
  echo "$nombre, podés votar."
else
  echo "$nombre, no podés votar todavía."
fi
