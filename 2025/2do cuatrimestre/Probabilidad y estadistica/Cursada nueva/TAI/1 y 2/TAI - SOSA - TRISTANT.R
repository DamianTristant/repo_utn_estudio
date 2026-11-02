# 1. IMPORTACION DE DATOS
library(readxl)
datos <- read_excel("TAI-aglomerado23.xlsx")
options(scipen = 999) 

# 2. VARIABLE IV1 (TIPO DE VIVIENDA) - CUALITATIVA NOMINAL

# Filtramos posibles valores nulos
IV1_limpio <- na.omit(datos$IV1)

# Calculo de frecuencias
fi_IV1 <- table(IV1_limpio)
hi_IV1 <- round(prop.table(fi_IV1), 4)

# Armado de la tabla
tabla_IV1 <- cbind(
  Frec_Absoluta = fi_IV1,
  Frec_Relativa = hi_IV1
)

print("Tabla de Frecuencias - IV1 (Tipo de Vivienda)")
print(tabla_IV1)

# 3. VARIABLE ITF (INGRESO TOTAL FAMILIAR) - CUANTITATIVA CONTINUA

# Filtramos hogares con ingresos validos (mayores o iguales a 0) y sin NA
ITF_limpio <- datos$ITF[datos$ITF >= 0 & !is.na(datos$ITF)]

# Calculo de cantidad optima de intervalos (Regla de Sturges manual)
n <- length(ITF_limpio)
k <- round(1 + 3.322 * log10(n))
print(paste("Cantidad de intervalos (k) calculados:", k))

# Agrupamiento en intervalos
intervalos_ITF <- cut(ITF_limpio, breaks = k, right = FALSE, include.lowest = TRUE, dig.lab = 10)

# Calculo de frecuencias para la tabla agrupada
fi_ITF <- table(intervalos_ITF)
hi_ITF <- round(prop.table(fi_ITF), 4)
Fi_ITF <- cumsum(fi_ITF)
Hi_ITF <- cumsum(hi_ITF)

# Armado de la tabla final
tabla_ITF <- cbind(
  Frec_Absoluta = fi_ITF, 
  Frec_Relativa = hi_ITF, 
  Frec_Abs_Acum = Fi_ITF, 
  Frec_Rel_Acum = Hi_ITF
)

print("Tabla de Frecuencias Agrupadas - ITF")
print(tabla_ITF)