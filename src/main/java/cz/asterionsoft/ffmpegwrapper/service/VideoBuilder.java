package cz.asterionsoft.ffmpegwrapper.service;

import cz.asterionsoft.ffmpegwrapper.exception.NotImplYet;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VideoBuilder implements Processor {
	private final Context context;
	private final EasyWrapper wrapper;
	private MultimediaBuilder parent;

	@Override
	public MultimediaBuilder back() {
		return parent;
	}

	@Override
	public VideoBuilder setInput(MultimediaBuilder parent, String fileName) {
		this.parent = parent;
		context.setInputVideoFileName(fileName);
		return this;
	}

	@Override
	public VideoBuilder setOutput(String fileName) {
		context.setOutputVideoFileName(fileName);
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

	@Override
	public VideoBuilder markOutputAsInput() {
		context.setInputVideoFileName(context.getOutputVideoFileName());
		context.setOutputVideoFileName(null);
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

	@Override
	public VideoBuilder convert() {
		wrapper.convert(context.getInputVideoFileName(), context.getOutputVideoFileName());
		return this;
	}

	@Override
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

	@Override
	public Processor merge(String secondFile, String outputFile) {
		throw new NotImplYet();
	}

	@Override
	public Processor append(String secondFile, String outputFile) {
		throw new NotImplYet();
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
