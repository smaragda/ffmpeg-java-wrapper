package cz.asterionsoft.ffmpegwrapper.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class EasyWrapper {

	private final CmdExecutor executor;


	/**
	 * "ffmpeg -i input.mp4 -ss 00:05:20 -t 00:10:00 -c:v copy -c:a copy output1.mp4"
	 *
	 * @param input
	 * @param output
	 * @param fromTime
	 * @param duration
	 */
	public void trim(String input, String output, String fromTime, String duration) {
		commonRun("ffmpeg -i %s -ss %s -t %s -c:v copy -c:a copy %s",
				input, fromTime, duration, output);
	}

	public void convert(String input, String output) {
		commonRun("ffmpeg -i %s %s",
				input, output);
	}

	/**
	 * ffmpeg -i input.mp4 -i subtitle.en.srt -c copy -c:s mov_text -metadata:s:s:0 language=eng ouptut_english.mp4
	 *
	 * @param input
	 * @param output
	 * @param srtFile
	 * @param lang
	 */
	public void softTitles(String input, String output, String srtFile, String lang) {
		commonRun("ffmpeg -i %s -i %s -c copy -c:s mov_text -metadata:s:s:0 language=%s %s",
				input, srtFile, lang, output);
	}

	/**
	 * ffmpeg -i input.mp4 -vf subtitles=subtitle.srt output_srt.mp4
	 *
	 * @param input
	 * @param output
	 * @param srtFile
	 */
	public void hardTitles(String input, String output, String srtFile) {
		commonRun("ffmpeg -i %s -vf subtitles=%s %s",
				input, srtFile, output);
	}

	public void version() {
		commonRun("ffmpeg -version");
	}

	private void commonRun(String template, String... paramsInCorrectOrder) {
		String command = String.format(template, (Object[]) paramsInCorrectOrder);
		executor.run(command);
	}

	List<String> getLastRunOuput() {
		return executor.getLastRunOutput();
	}
}
