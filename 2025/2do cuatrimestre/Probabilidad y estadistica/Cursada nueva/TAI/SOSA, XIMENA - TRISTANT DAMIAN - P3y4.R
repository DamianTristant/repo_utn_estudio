# 1. IMPORTACION DE DATOS
library(readxl)
datos <- read_excel("TAI-aglomerado23.xlsx")
options(scipen = 999) 

# -------------------------------------------------------------
# PARTE 1: TABLAS DE FRECUENCIAS (Entregado en Semana 3)
# -------------------------------------------------------------

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

# -------------------------------------------------------------
# PARTE 2: MEDIDAS Y GRÁFICOS (Entrega Semana 5)
# -------------------------------------------------------------

# --- Consigna 3: Medidas Descriptivas ---

# Cálculos para ITF (Variable Cuantitativa Continua)
media_ITF <- round(mean(ITF_limpio), 4)
mediana_ITF <- round(median(ITF_limpio), 4)
cuartiles_ITF <- round(quantile(ITF_limpio, probs = c(0.25, 0.5, 0.75)), 4)

rango_ITF <- max(ITF_limpio) - min(ITF_limpio)
varianza_ITF <- round(var(ITF_limpio), 4)
desvio_ITF <- round(sd(ITF_limpio), 4)
cv_ITF <- round((desvio_ITF / media_ITF) * 100, 4)

print("--- Medidas Descriptivas ITF ---")
cat("Media:", media_ITF, "\n")
cat("Mediana:", mediana_ITF, "\n")
cat("Cuartiles (Q1, Q2, Q3):", cuartiles_ITF, "\n")
cat("Rango:", rango_ITF, "\n")
cat("Varianza:", varianza_ITF, "\n")
cat("Desvío Estándar:", desvio_ITF, "\n")
cat("Coeficiente de Variación (%):", cv_ITF, "\n\n")

# Cálculo para IV1 (Solo Moda por ser cualitativa)
# Buscamos cuál es el valor que más se repite en la tablita que armamos antes
moda_IV1 <- names(which.max(fi_IV1))

print("--- Medidas Descriptivas IV1 ---")
cat("Moda IV1:", moda_IV1, "(Corresponde a la categoría 'Casa')\n\n")


# --- Consigna 4: Gráficos ---

# 4.1 Histograma ITF (usando frecuencias absolutas)
hist(ITF_limpio, 
     breaks = k, 
     main = "Distribución del Ingreso Total Familiar", 
     xlab = "Ingresos ($)", 
     ylab = "Frecuencia Absoluta", 
     col = "lightblue", 
     border = "black")

# 4.2 Gráfico Circular IV1
# Preparamos los textos sumando el % al lado del nombre para que quede más completo
etiquetas_IV1 <- c("Casa", "Departamento", "Pieza en inquilinato")
porcentajes_IV1 <- round(hi_IV1 * 100, 2)
etiquetas_pie <- paste(etiquetas_IV1, porcentajes_IV1, "%")

pie(fi_IV1, 
    labels = etiquetas_pie, 
    main = "Distribución de Hogares por Tipo de Vivienda", 
    col = c("#66c2a5", "#fc8d62", "#8da0cb"))
