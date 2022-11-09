package cz.asterionsoft.ffmpegwrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FFMpegWrapper {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(FFMpegWrapper.class);
		application.run(args);
	}
}
