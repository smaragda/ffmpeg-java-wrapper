package cz.asterionsoft.ffmpegwrapper;

import cz.asterionsoft.ffmpegwrapper.service.VideoBuilder;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class IndependentRunner {

	@EventListener
	public void run(ApplicationStartedEvent event) {
		//addSoftTitles(event.getApplicationContext().getBean(VideoBuilder.class));
		pictureSeries(event.getApplicationContext().getBean(VideoBuilder.class));
	}

	private void pictureSeries(VideoBuilder builder) {
		builder
				.outputVideo("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/sofi-pic-series-3.mp4")
				.makeVideoFromPictureSeries("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/series%d.jpg")
				.inputVideo("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/sofi-pic-series-3.mp4")
				.outputVideo("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/series-titles-1.mp4")
				.softTitles("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/pokus.srt");
	}

	private void addSoftTitles(VideoBuilder videoBuilder) {
		videoBuilder
				.inputVideo("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/sofi-hvezda-cela-postava.mp4")
				.outputVideo("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/sofi-hvezda-titles.mp4")
				.softTitles("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/pokus.srt");
	}

}
