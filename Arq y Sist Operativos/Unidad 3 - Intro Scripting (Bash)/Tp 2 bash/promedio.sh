#!/bin/bash

suma=0

for i in {1..5}
do
  read -p "Ingresá el número $i: " num
  suma=$((suma + num))
done

promedio=$(echo "scale=2; $suma / 5" | bc)

echo "El promedio es: $promedio"

