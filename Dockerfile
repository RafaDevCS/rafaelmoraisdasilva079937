# Estágio 1: Build (Compilação)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia apenas o pom.xml primeiro para baixar as dependências (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Execução)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Instala o curl para o Health Check do Docker funcionar
RUN apk add --no-cache curl

# Copia o JAR gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Define o fuso horário (opcional, mas recomendado)
ENV TZ=America/Sao_Paulo

# Expõe a porta padrão do Spring
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]