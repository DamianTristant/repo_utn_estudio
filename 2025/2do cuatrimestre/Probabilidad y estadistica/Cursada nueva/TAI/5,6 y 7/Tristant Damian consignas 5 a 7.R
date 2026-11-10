library(readxl)

datos <- read_excel(file.choose())

# CONSIGNA 5: DISTRIBUCIÓN HIPERGEOMÉTRICA

N <- nrow(datos)                       # Población total (330)
K <- sum(datos$II7 == 3)               # Hogares inquilinos (55)
n <- 15                                # Muestra (15)

cat("--- CONSIGNA 5 ---\n")
# 5.1: Probabilidad de que exactamente 4 hogares sean inquilinos
p5_1 <- dhyper(x = 4, m = K, n = N - K, k = n)
cat("5.1 P(X = 4):", round(p5_1, 4), "\n")

# 5.2: Probabilidad de que más de 8 hogares sean inquilinos P(X > 8)
p5_2 <- phyper(q = 8, m = K, n = N - K, k = n, lower.tail = FALSE)
cat("5.2 P(X > 8):", round(p5_2, 4), "\n")

# 5.3: Probabilidad de que a lo sumo 6 hogares sean inquilinos P(X <= 6)
p5_3 <- phyper(q = 6, m = K, n = N - K, k = n, lower.tail = TRUE)
cat("5.3 P(X <= 6):", round(p5_3, 4), "\n\n")

# CONSIGNA 6: DISTRIBUCIÓN NORMAL

media_itf <- mean(datos$ITF, na.rm = TRUE)
sd_itf    <- sd(datos$ITF, na.rm = TRUE)

cat("--- CONSIGNA 6 ---\n")
# 6.1: Probabilidad de ITF por lo menos 350.000 -> P(X >= 350.000)
p6_1 <- pnorm(350000, mean = media_itf, sd = sd_itf, lower.tail = FALSE)
cat("6.1 P(X >= 350.000):", round(p6_1, 4), "\n")

# 6.2: Probabilidad de ITF entre 250.000 y 300.000 -> P(250.000 <= X <= 300.000)
p6_2 <- pnorm(300000, mean = media_itf, sd = sd_itf) - 
  pnorm(250000, mean = media_itf, sd = sd_itf)
cat("6.2 P(250.000 <= ITF <= 300.000):", round(p6_2, 4), "\n")

# 6.3: Probabilidad de ITF menor que 450.000 -> P(X < 450.000)
p6_3 <- pnorm(450000, mean = media_itf, sd = sd_itf, lower.tail = TRUE)
cat("6.3 P(X < 450.000):", round(p6_3, 4), "\n\n")

# CONSIGNA 7: DISTRIBUCIÓN DE POISSON

# Tasa base: 12 formularios por hora (60 min) -> 0.2 por minuto

cat("--- CONSIGNA 7 ---\n")
# 7.1: Exactamente 4 formularios en 15 minutos (lambda = 12 * 15/60 = 3)
lambda_15min <- 12 * (15 / 60)
p7_1 <- dpois(x = 4, lambda = lambda_15min)
cat("7.1 P(Y = 4 en 15 min):", round(p7_1, 4), "\n")

# 7.2: Como máximo 5 formularios en 30 minutos (lambda = 12 * 30/60 = 6) -> P(Y <= 5)
lambda_30min <- 12 * (30 / 60)
p7_2 <- ppois(q = 5, lambda = lambda_30min, lower.tail = TRUE)
cat("7.2 P(Y <= 5 en 30 min):", round(p7_2, 4), "\n")

# 7.3: Menos de 7 formularios en 45 minutos (lambda = 12 * 45/60 = 9) -> P(Y < 7) = P(Y <= 6)
lambda_45min <- 12 * (45 / 60)
p7_3 <- ppois(q = 6, lambda = lambda_45min, lower.tail = TRUE)


cat("7.3 P(Y < 7 en 45 min):", round(p7_3, 4), "\n")