package cz.asterionsoft.ffmpegwrapper.easy;

import java.util.List;

public class VideoBuilder {

	/**
	 * singleton
	 */
	private static final VideoBuilder INSTANCE = new VideoBuilder();

	private final Context context;

	private final EasyWrapper wrapper;

	public static VideoBuilder getInstance() {
		return INSTANCE;
	}

	private VideoBuilder() {
		this.wrapper = new EasyWrapper();
		this.context = new Context();
	}


	public VideoBuilder version() {
		wrapper.version();
		return this;
	}

	public List<String> getLastRunOutput() {
		return wrapper.getLastRunOuput();
	}

	public VideoBuilder inputVideo(String fileName) {
		context.setInputVideoFileName(fileName);
		return this;
	}

	public VideoBuilder outputVideo(String fileName) {
		context.setOutputVideoFileName(fileName);
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
}
