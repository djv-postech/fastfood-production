FROM amazoncorretto:17-alpine-jdk
ADD target/fastfood-production-0.0.1.jar fastfood-production.jar
ENTRYPOINT ["java", "-jar", "fastfood-production.jar"]
EXPOSE 8090