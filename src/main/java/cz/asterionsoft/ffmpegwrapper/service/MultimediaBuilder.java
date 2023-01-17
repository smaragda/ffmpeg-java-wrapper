package cz.asterionsoft.ffmpegwrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MultimediaBuilder {

	private final VideoBuilder videoBuilder;
	private final AudioBuilder audioBuilder;
	private final EasyWrapper wrapper;

	public MultimediaBuilder version() {
		wrapper.version();
		return this;
	}

	public List<String> getLastRunOutput() {
		return wrapper.getLastRunOutput();
	}

	public VideoBuilder useVideo(String videoFile) {
		return videoBuilder.setInput(this, videoFile);
	}

	public AudioBuilder useAudio(String audioFile) {
		return audioBuilder.setInput(this, audioFile);
	}
}
