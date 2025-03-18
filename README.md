# FFmpeg Java Wrapper

A Spring Boot application that provides a REST API wrapper around FFmpeg, allowing for seamless manipulation of multimedia files through a web interface.

## Overview

This application serves as a bridge between your applications and FFmpeg's powerful multimedia processing capabilities. It exposes RESTful endpoints to perform common operations like:

- Video to image extraction
- Audio extraction
- Replacing audio in videos
- Creating videos from image sequences
- Video trimming
- Adding subtitles (soft and hard)
- Audio merging

## Tech Stack

- **Java 21**
- **Spring Boot 3.1.5**
- **Maven**
- **Docker**
- **FFmpeg**
- **Lombok**
- **Swagger/OpenAPI**

## Prerequisites

- JDK 21
- Maven
- FFmpeg installed (or use Docker which includes FFmpeg)

## Getting Started

### Running with Docker

The simplest way to run the application:

```bash
# Build the Docker image
docker build -t ffmpeg-wrapper .

# Run the container
docker run -p 8080:8080 ffmpeg-wrapper
```

### Running Locally

```bash
# Compile and package
mvn clean package

# Run the application
java -jar target/ffmpeg-java-wrapper-1.1-SNAPSHOT.jar
```

Make sure FFmpeg is installed and available in your system's PATH.

## API Endpoints

### Check FFmpeg Version
```
GET /version
```
Returns the installed FFmpeg version and capabilities.

### Upload File
```
POST /upload
Content-Type: multipart/form-data
```
Upload a multimedia file for processing.

#### Example Response:
```json
{
  "message": "File video.mp4 uploaded successfully",
  "status": "OK",
  "uuid": "550e8400-e29b-41d4-a716-446655440000",
  "files": ["550e8400-e29b-41d4-a716-446655440000.mp4"]
}
```

### Extract Images from Video
```
POST /pics/{uuid}
```
Split a video into a series of image frames (1 frame per second).

## Architecture

The application follows a builder pattern to create clean, readable chains of FFmpeg operations:

```java
builder
    .useVideo("input.mp4")
    .trim("00:01:00", "00:00:30")
    .setOutput("output.mp4")
    .convert()
    .back();
```

### Core Components

- **MultimediaBuilder**: Entry point for creating operation chains
- **VideoBuilder/AudioBuilder**: Handle specific media type operations
- **EasyWrapper**: Translates high-level operations to FFmpeg commands
- **CmdExecutor**: Executes FFmpeg commands safely
- **Context**: Maintains state of the current operation chain
- **FileService**: Handles file operations and storage

## File Storage

The application creates two directories:
- **`in/`**: For uploaded files
- **`out/`**: For processed outputs

Processed files are accessible via the `/out/**` path.

## Configuration

Key application properties (in `application.properties`):

```properties
# Server configuration
server.port=8080

# Logging
logging.level.cz.asterionsoft.ffmpegwrapper=DEBUG

# Upload file size limits
spring.servlet.multipart.max-file-size=100MB  
spring.servlet.multipart.max-request-size=100MB
spring.servlet.multipart.file-size-threshold=2KB
```

## Example Usage

### Extract Audio from Video

```bash
# First upload a video
curl -X POST -F "file=@video.mp4" http://localhost:8080/upload

# Extract images (replace UUID with the one returned from upload)
curl -X POST http://localhost:8080/pics/550e8400-e29b-41d4-a716-446655440000
```

## Extending the Application

The application is designed to be easily extended:

1. Add new methods to `VideoBuilder` or `AudioBuilder`
2. Implement corresponding methods in `EasyWrapper`
3. Expose new endpoints in controllers as needed

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- [FFmpeg](https://ffmpeg.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
