#!/bin/bash

read -p "Ingresá una palabra: " palabra

longitud=${#palabra}

echo "La palabra '$palabra' tiene $longitud caracteres."
