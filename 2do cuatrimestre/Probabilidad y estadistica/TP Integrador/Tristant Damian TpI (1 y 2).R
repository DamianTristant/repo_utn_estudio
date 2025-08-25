library(readxl)
archivo <- file.choose()
datos <- read_excel(archivo)

columna_tiempos <- datos$`TIEMPO SEMANAL en HS. DEDIC. EST.`
columna_tiempos <- na.omit(columna_tiempos)
longitud <- length(columna_tiempos)
longitud

k <- ceiling(1 + 3.322 * log10(longitud))
k
rango <- range(columna_tiempos)
amplitud <- ceiling((rango[2] - rango[1])/ k)
rango
amplitud

breaks <- seq(floor(rango[1]),ceiling(rango[2]) + amplitud, by = amplitud)
clases <- cut(columna_tiempos, breaks = breaks, right = FALSE)
head(clases)
tabla_tiempo <- table(clases)
f_acum <- cumsum(tabla_tiempo)
f_rel <- prop.table(tabla_tiempo)
f_rel_acum <- cumsum(f_rel)

tabla_final <- data.frame(
  Intervalo = levels(clases),
  Frecuencia = as.vector(tabla_tiempo),
  Frec_Acumulada = as.vector(f_acum),
  Frec_Relativa = round(as.vector(f_rel),4),
  Frec_Rel_Acum = round(as.vector(f_rel_acum),4)
)

tabla_final
print(tabla_final, row.names = FALSE)

tabla_satisfaccion <- datos$`SATISFACCIÓN CON LA CARRERA`
unique(tabla_satisfaccion)

tabla_satisfaccion <- factor(tabla_satisfaccion,
                             levels = c(1,2,3,4),
                             labels = c("Muy satisfecho",
                                        "Satisfecho",
                                        "Insatisfecho",
                                        "Muy insatisfecho"),
                             ordered = TRUE)
tabla_satisfaccion
tabla_satisfaccion <- table(tabla_satisfaccion)
f_acum <- cumsum(tabla_satisfaccion)
f_rel <- prop.table(tabla_satisfaccion)
f_rel_acum <- cumsum(f_rel)


print(tabla_satisfaccion, row.names = FALSE)
