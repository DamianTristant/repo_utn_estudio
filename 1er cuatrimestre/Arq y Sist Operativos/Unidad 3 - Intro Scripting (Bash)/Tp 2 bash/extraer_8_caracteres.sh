#!/bin/bash

read -p "Ingresá una cadena: " cadena


subcadena=${cadena:0:8}


echo "Los primeros 8 caracteres son: $subcadena"
