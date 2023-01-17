package cz.asterionsoft.ffmpegwrapper;

import cz.asterionsoft.ffmpegwrapper.service.MultimediaBuilder;
import cz.asterionsoft.ffmpegwrapper.service.VideoBuilder;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class IndependentRunner {

	@EventListener
	public void run(ApplicationStartedEvent event) {
		//addSoftTitles(event.getApplicationContext().getBean(VideoBuilder.class));
		//pictureSeries(event.getApplicationContext().getBean(VideoBuilder.class));
		//reklamniVideo(event.getApplicationContext().getBean(VideoBuilder.class));
	}

	private void pictureSeries(VideoBuilder builder) {
		builder
				.setOutput("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/sofi-pic-series-3.mp4")
				.makeVideoFromPictureSeries("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/series%d.jpg")
				.markOutputAsInput()
				.setOutput("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/series-titles-1.mp4")
				.softTitles("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/titulky.srt");
	}

	private void addSoftTitles(MultimediaBuilder builder) {
		builder
				.useVideo("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/sofi-hvezda-cela-postava.mp4")
				.setOutput("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/sofi-hvezda-titles.mp4")
				.softTitles("/Users/janmarcis/source/ffmpeg-java-wrapper/src/main/resources/titulky.srt");
	}
}
