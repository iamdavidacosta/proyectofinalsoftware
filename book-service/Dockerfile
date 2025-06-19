FROM openjdk:24-jdk

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn

COPY pom.xml .

RUN ./mvnw dependency:go-offline

COPY src ./src

COPY src/main/resources/application.properties ./src/main/resources/application.properties

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/book-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8090