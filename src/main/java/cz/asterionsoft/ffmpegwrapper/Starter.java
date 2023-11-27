package cz.asterionsoft.ffmpegwrapper;

import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Starter {

	@EventListener
	public void run(ApplicationStartedEvent event) {
		//addSoftTitles(event.getApplicationContext().getBean(VideoBuilder.class));
		//pictureSeries(event.getApplicationContext().getBean(VideoBuilder.class));
		//reklamniVideo(event.getApplicationContext().getBean(VideoBuilder.class));

		// create IN directory for uploading videos
		createDirs("in");
		// create OUT directory for exploded files (mp3, jpg)
		createDirs("out");
	}

	@SneakyThrows
	private void createDirs(String dirName) {
		Path path = Paths.get(dirName);
		Files.createDirectories(path);
	}
}
