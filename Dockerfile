# Use a Maven base image to build the project
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline
COPY . .
RUN mvn -B -e -o -T 1C verify

# Compile and package the application to an executable JAR file
RUN mvn clean package

# Use an official Ubuntu runtime as a parent image
#FROM ubuntu:latest
FROM eclipse-temurin:21-jdk

# Set the maintainer label
LABEL maintainer="postmaster@asterionsoft.cz"

# Update Ubuntu Software repository
RUN apt update

# Install FFmpeg
RUN apt install -y ffmpeg

# Copy the built JAR file from the build stage
COPY --from=build /home/app/target/*.jar app.jar

# The port the app runs on
EXPOSE 8080


# Command to run the application
ENTRYPOINT ["java","-jar","/app.jar"]
