FROM java:8
ADD target/tim-rest-service-0.1.0.jar app.jar
EXPOSE 7777
ENTRYPOINT java -jar -Djava.security.egd=file:/dev/./urandom /app.jar 
