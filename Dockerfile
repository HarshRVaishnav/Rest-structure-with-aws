FROM openjdk:11
EXPOSE 8080
ARG VERSION=0.0.1-SNAPSHOT	
WORKDIR /app/
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src
ADD target/file_uploading_amazon_s3.jar file_uploading_amazon_s3.jar

#COPY --from=BUILDER /build/target/application.jar /app/
CMD java -jar /app/application.jar
ADD target/*.jar mysql.jar
ENTRYPOINT ["java","-jar","/file_uploading_amazon_s3.jar"]	