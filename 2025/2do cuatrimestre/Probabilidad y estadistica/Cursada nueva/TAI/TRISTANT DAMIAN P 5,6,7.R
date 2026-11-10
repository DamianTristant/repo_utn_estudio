library(readxl)
datos <- read_excel(file.choose())

# CONSIGNA 5: DISTRIBUCIÓN HIPERGEOMÉTRICA

# Contexto: Selección aleatoria de n = 15 hogares sin reposición de un total N = 330.
# II7 == 3 indica hogar Inquilino / Arrendatario.

N <- nrow(datos)                       # Población total (330)
K <- sum(datos$II7 == 3)               # Éxitos en la población (Inquilinos = 55)
n <- 15                                # Tamaño de la muestra sin reposición

cat("--- CONSIGNA 5: Hipergeométrica ---\n")
cat("N (Población):", N, "\n")
cat("K (Total Inquilinos):", K, "\n")
cat("n (Muestra):", n, "\n\n")

# a) Probabilidad de obtener exactamente 3 hogares inquilinos: P(X = 3)
p_exacto_3 <- dhyper(x = 3, m = K, n = N - K, k = n)
cat("P(X = 3):", round(p_exacto_3, 4), "\n")

# b) Probabilidad de obtener al menos 2 hogares inquilinos: P(X >= 2) = 1 - P(X <= 1)
p_al_menos_2 <- 1 - phyper(q = 1, m = K, n = N - K, k = n)
cat("P(X >= 2):", round(p_al_menos_2, 4), "\n")

# c) Esperanza y Varianza de la muestra
esperanza_hyper <- n * (K / N)
varianza_hyper  <- n * (K / N) * (1 - (K / N)) * ((N - n) / (N - 1))
cat("Esperanza E(X):", round(esperanza_hyper, 4), "\n")
cat("Varianza V(X):", round(varianza_hyper, 4), "\n\n")

# CONSIGNA 6: DISTRIBUCIÓN NORMAL
# Contexto: Análisis del Ingreso Total Familiar (ITF) de los 330 hogares.

media_itf <- mean(datos$ITF, na.rm = TRUE)
sd_itf    <- sd(datos$ITF, na.rm = TRUE)

cat("--- CONSIGNA 6: Normal ---\n")
cat("Media (mu):", round(media_itf, 2), "ARS\n")
cat("Desviación estándar (sigma):", round(sd_itf, 2), "ARS\n\n")

# a) Probabilidad de que un hogar tenga ITF mayor a $1.500.000: P(X > 1500000)
p_itf_mayor_1500k <- pnorm(1500000, mean = media_itf, sd = sd_itf, lower.tail = FALSE)
cat("P(ITF > 1.500.000):", round(p_itf_mayor_1500k, 4), "\n")

# b) Probabilidad de que un hogar tenga ITF entre $700.000 y $2.000.000
p_itf_entre_700k_2000k <- pnorm(2000000, mean = media_itf, sd = sd_itf) - 
  pnorm(700000, mean = media_itf, sd = sd_itf)
cat("P(700.000 <= ITF <= 2.000.000):", round(p_itf_entre_700k_2000k, 4), "\n")

# c) Ingreso mínimo del 10% de los hogares de mayores ingresos (Percentil 90)
q_top_10 <- qnorm(0.10, mean = media_itf, sd = sd_itf, lower.tail = FALSE)
cat("Umbral 10% más alto (Percentil 90): $", round(q_top_10, 2), "\n\n")

# CONSIGNA 7: DISTRIBUCIÓN DE POISSON
# Contexto: Cantidad de formularios digitales recibidos por el servidor central del INDEC.

# Ejemplo con una tasa promedio lambda = 4 formularios por hora:
lambda_hora <- 4

cat("--- CONSIGNA 7: Poisson ---\n")
cat("Lambda por hora:", lambda_hora, "\n\n")

# a) Probabilidad de recibir exactamente 5 formularios en 1 hora: P(Y = 5)
p_poisson_5_1h <- dpois(x = 5, lambda = lambda_hora)
cat("P(Y = 5 en 1 hora):", round(p_poisson_5_1h, 4), "\n")

# b) Probabilidad de recibir más de 10 formularios en 3 horas: P(Y > 10)
# Ajuste de lambda para 3 horas: lambda_3h = lambda_hora * 3
lambda_3h <- lambda_hora * 3
p_poisson_mas_10_3h <- ppois(q = 10, lambda = lambda_3h, lower.tail = FALSE)
cat("P(Y > 10 en 3 horas con lambda =", lambda_3h, "):", round(p_poisson_mas_10_3h, 4), "\n")

