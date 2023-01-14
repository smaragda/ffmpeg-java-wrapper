package cz.asterionsoft.ffmpegwrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoBuilder {
	private final Context context;
	private final EasyWrapper wrapper;


	public VideoBuilder version() {
		wrapper.version();
		return this;
	}

	public List<String> getLastRunOutput() {
		return wrapper.getLastRunOutput();
	}

	public VideoBuilder inputVideo(String fileName) {
		context.setInputVideoFileName(fileName);
		return this;
	}

	public VideoBuilder inputAudio(String fileName) {
		context.setInputAudioFileName(fileName);
		return this;
	}

	public VideoBuilder mergeTwoAudios(String audioFile2, String outputAudio) {
		wrapper.mergeTwoAudios(
				context.getInputAudioFileName(),
				audioFile2,
				outputAudio
		);
		return this;
	}

	public VideoBuilder splitVideoInPictureSeries(String picBaseName) {
		wrapper.splitVideoToPictureSeries(
				context.getInputVideoFileName(),
				picBaseName
		);
		return this;
	}

	public VideoBuilder extractAudio(String outputAudio) {
		context.setOutputAudioFileName(outputAudio);
		wrapper.extractAudio(
				context.getInputVideoFileName(),
				context.getOutputAudioFileName()
		);
		return this;
	}

	public VideoBuilder markVideoOutputAsInput() {
		context.setInputVideoFileName(context.getOutputVideoFileName());
		context.setOutputVideoFileName(null);
		return this;
	}

	public VideoBuilder markAudioOutputAsInput() {
		context.setInputAudioFileName(context.getOutputAudioFileName());
		context.setOutputAudioFileName(null);
		return this;
	}

	public VideoBuilder outputVideo(String fileName) {
		context.setOutputVideoFileName(fileName);
		return this;
	}

	public VideoBuilder outputAudio(String fileName) {
		context.setOutputAudioFileName(fileName);
		return this;
	}

	public VideoBuilder replaceAudio(String audioFile) {
		wrapper.replaceAudioInVideo(
				context.getInputVideoFileName(),
				audioFile,
				context.getOutputVideoFileName()
		);
		return this;
	}

	public VideoBuilder convert() {
		wrapper.convert(context.getInputVideoFileName(), context.getOutputVideoFileName());
		return this;
	}

	public VideoBuilder trim(String fromTime, String toTime) {
		wrapper.trim(
				context.getInputVideoFileName(),
				context.getOutputVideoFileName(),
				fromTime,
				toTime
		);
		context.setFromTime(fromTime);
		context.setToTime(toTime);
		return this;
	}

	public VideoBuilder softTitles(String srtFile) {
		wrapper.softTitles(
				context.getInputVideoFileName(),
				context.getOutputVideoFileName(),
				srtFile,
				"cze"
		);
		return this;
	}

	public VideoBuilder makeVideoFromPictureSeries(String fileNameNumbered) {
		wrapper.makeVideoFromPictureSeries(
				"1",
				fileNameNumbered,
				context.getOutputVideoFileName()
		);
		return this;
	}

}
