# Usa Maven con JDK 17 como imagen base
FROM maven:3.8.6-amazoncorretto-17 AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar archivos del proyecto
COPY . .

# Construir la aplicación
RUN mvn clean package -DskipTests

# Segunda fase: imagen final con solo el JAR
FROM amazoncorretto:17
WORKDIR /app

# Copiar el JAR desde la fase anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
