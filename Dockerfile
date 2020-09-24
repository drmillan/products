FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
CMD mkdir /app
COPY products-app/target/products.jar /app/products.jar
ENTRYPOINT ["java","-jar","/app/products.jar"]