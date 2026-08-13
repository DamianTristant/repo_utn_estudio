library(readxl)
archivo <- file.choose()
datos <- read_excel(archivo)

#Creo la columna tiempos y omito datos faltantes.
columna_tiempos <- datos$`TIEMPO SEMANAL en HS. DEDIC. EST.`
columna_tiempos <- na.omit(columna_tiempos)

#Variable longitud y le asigno la longitud total de la variable col tiempos.
longitud <- length(columna_tiempos)
longitud

#Regla de sturges para cantidad de clases
k <- ceiling(1 + 3.322 * log10(longitud))
k

#Funcion range que toma valor min y max
rango <- range(columna_tiempos)

#Amplitud de cada clase
amplitud <- ceiling((rango[2] - rango[1])/ k)
rango
amplitud

#Secuencia de cortes de cada intervalo de a 1 amplitud por vez
breaks <- seq(floor(rango[1]),ceiling(rango[2]) + amplitud, by = amplitud)

#Funcion para asignar a cada dato el intervalo correspondiente
clases <- cut(columna_tiempos, breaks = breaks, right = FALSE, include.lowest = TRUE )
marca_clase <- (head(breaks, -1) + tail(breaks, -1)) / 2

#Creacion tabla con las frecuencias
tabla_tiempo <- table(clases)
f_acum <- cumsum(tabla_tiempo)
f_rel <- prop.table(tabla_tiempo)
f_rel_acum <- cumsum(f_rel)

#Data frame final
tabla_final <- data.frame(
  Intervalo = levels(clases),
  Marca_Clase = as.vector(marca_clase),
  Frec_Abs = as.vector(tabla_tiempo),
  Frec_Acumulada = as.vector(f_acum),
  Frec_Relativa = round(as.vector(f_rel), 4),
  Frec_Rel_Acum = round(as.vector(f_rel_acum), 4)
)

print(tabla_final, row.names = FALSE)


#Creacion tabla satisfaccion
tabla_satisfaccion <- datos$`SATISFACCIÓN CON LA CARRERA`
unique(tabla_satisfaccion)

#reemplazo valores enteros por nombres
tabla_satisfaccion <- factor(tabla_satisfaccion,
                             levels = c(1,2,3,4),
                             labels = c("Muy satisfecho",
                                        "Satisfecho",
                                        "Insatisfecho",
                                        "Muy insatisfecho"),
                             ordered = TRUE)

#tabla con sus frecuencias
tabla_satisfaccion <- table(tabla_satisfaccion)
f_acum <- cumsum(tabla_satisfaccion)
f_rel <- prop.table(tabla_satisfaccion)
f_rel_acum <- cumsum(f_rel)

print(tabla_satisfaccion, row.names = FALSE)

nivel_satisfaccion <- data.frame(
  Nivel_Satisfaccion = names(tabla_satisfaccion),
  Frec_Abs = as.vector(tabla_satisfaccion),
  Frec_Acum = as.vector(f_acum),
  Frec_Rel = round(as.vector(f_rel), 4),
  Frec_Acum_Rel = round(as.vector(f_rel_acum), 4)
)

print(nivel_satisfaccion, row.names = FALSE)


#Medidas para variable continua

#Calculo media
frecuencias <- as.vector(tabla_tiempo)
media_continua <- sum(marca_clase * frecuencias) / sum(frecuencias)

#calculo moda
i_modal <- which.max(frecuencias)   #indice de la clase modal
L_m <- breaks[i_modal]              #Lim inferior del intervalor modal
f_m <- frecuencias[i_modal]         #Frecuencua intervalo modal
f_1 <- ifelse(i_modal == 1, 0, frecuencias[i_modal - 1]) #Frecuencia anterior
f_2 <- ifelse(i_modal == length(frecuencias), 0, frecuencias[i_modal + 1])   #frecuencia posterior
moda_continua <- L_m + ((f_m - f_1) / ((f_m - f_1) + (f_m - f_2))) * amplitud  #Formula interpolacion

#calculo mediana
n_total <- sum(frecuencias)
n_2 <- n_total / 2
clase_mediana_index <- which(f_acum >= n_2) [1]
L <- breaks[clase_mediana_index] #limite inferior del intervalo de la mediana
F_anterior <- ifelse(clase_mediana_index == 1, 0, f_acum[clase_mediana_index - 1])
f_mediana <- frecuencias[clase_mediana_index]
mediana_continua <- L + ((n_2 - F_anterior) / f_mediana) * amplitud #Formula interporal

#medidas de dispersion
varianza_continua <- sum(frecuencias * (marca_clase - media_continua) ^2) / (n_total - 1)
desvio_continua <- sqrt(varianza_continua)
coef_var_continua <- (desvio_continua/media_continua) * 100

#data frame con los resultados
continua_stats <- data.frame(
  Media = round(media_continua, 4),
  Moda = round(moda_continua, 4),
  Mediana = round(mediana_continua, 4),
  Varianza = round(varianza_continua, 4),
  Desvio_Estandar = round(desvio_continua, 4),
  Coef_Variacion_pct = round(coef_var_continua, 4)
)

print(continua_stats, row.names = FALSE)

#Medidas variable discreta

variable_discreta <- "SATISFACCIÓN CON LA CARRERA" #Nombre de la variable discreta a analizar

#Mediana
mediana_discreta <- median(datos[[variable_discreta]], na.rm = TRUE)

#Moda
library(modeest)
moda_discreta <- mlv(datos[[variable_discreta]], method = "mfv")

#Tabla con las medidas
discreta_stats <- data.frame(
  Mediana = mediana_discreta,
  Moda = moda_discreta
)

print(discreta_stats, row.names = FALSE)

#Medidas de posicion
cuartiles <- quantile(datos[[variable_discreta]], probs = c(0.25, 0.5, 0.75), na.rm = TRUE)
rango_intercuartil <- IQR(datos[[variable_discreta]], na.rm = TRUE)
cuartiles
cat("RIC:",rango_intercuartil,"\n")
