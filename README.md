# ffmpeg-java-wrapper
wrapper for ffmpeg to easier work with it as java dev

Also expose ffmpeg functionality.

## usage
POST /upload
uploads mulipart video file to server. as a return there is a UUID which will be used later.

POST /pics/{UUID} 
split uploaded video into series of png files. output picture names are returned here.

GET /out/{UUID}-pic0001.png
exposed picture series starting from 1 as a static resources accessible thru above GET. 
