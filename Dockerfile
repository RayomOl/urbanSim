FROM node:20-alpine AS frontend-build

WORKDIR /frontend
COPY frontend/package*.json ./
RUN npm ci
COPY frontend/ ./
RUN npm run build

FROM maven:3.9.9-eclipse-temurin-21 AS backend-build

WORKDIR /backend
COPY backend/pom.xml ./
COPY backend/.mvn/ .mvn/
COPY backend/mvnw backend/mvnw.cmd ./
RUN mvn dependency:go-offline -B
COPY backend/src/ src/
COPY --from=frontend-build /frontend/dist/ src/main/resources/static/
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=backend-build /backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
