
COPY pom.xml /app/
RUN mvn dependency:go-offline

COPY src/main /app/src/main/
COPY src/test /app/src/test/

WORKDIR /app
RUN mvn clean install -DskipTests

FROM openjdk:11-jre-slim

RUN apt-get update && apt-get install -y curl

WORKDIR /app

COPY --from=build /app/target/medusa_merchant-0.0.1-SNAPSHOT.jar portal.jar

EXPOSE 1837

ENTRYPOINT ["java","-jar","portal.jar"]