# Use an official Ubuntu runtime as a parent image
#FROM ubuntu:latest
FROM eclipse-temurin:21-jdk

# Set the maintainer label
LABEL maintainer="your-email@example.com"

# Update Ubuntu Software repository
RUN apt update

# Install FFmpeg
RUN apt install -y ffmpeg

# Install OpenJDK 11
#RUN apt install -y openjdk-11-jdk

# Set JAVA_HOME environment variable
#ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64

# Set PATH to include the Java bin directory
#ENV PATH $JAVA_HOME/bin:$PATH

# The port the app runs on
EXPOSE 8080

# Copy the JAR file into the image
COPY target/ffmpeg-java-wrapper-1.1-SNAPSHOT.jar app.jar

# Command to run the application
ENTRYPOINT ["java","-jar","/app.jar"]
