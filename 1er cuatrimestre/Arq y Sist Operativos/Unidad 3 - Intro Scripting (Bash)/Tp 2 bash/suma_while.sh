#!/bin/bash

suma=0
numero=1

while [ $numero -le 100 ]
do
  suma=$((suma + numero))
  numero=$((numero + 1))
done

echo "La suma de los números del 1 al 100 es: $suma"


