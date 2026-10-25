FROM maven:3.8.4-eclipse-temurin-17 as BUILD
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17
WORKDIR /app
ENV EMPLOYEE_DB=production
COPY --from=BUILD /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
