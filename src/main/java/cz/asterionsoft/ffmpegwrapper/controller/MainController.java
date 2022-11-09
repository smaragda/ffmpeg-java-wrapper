package cz.asterionsoft.ffmpegwrapper.controller;

import cz.asterionsoft.ffmpegwrapper.service.OutputCondition;
import cz.asterionsoft.ffmpegwrapper.service.VideoBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

	private final VideoBuilder videoBuilder;
	private final OutputCondition condition;


	@GetMapping(value = "/ffmpeg-version", produces = "text/html")
	public String getFFMpegVersion() {
		videoBuilder.version();
		return videoBuilder
				.getLastRunOutput()
				.stream()
				.map(s -> s + "<br/>")
				.reduce(String::concat)
				.orElse("<NO OUTPUT>");
	}

	@GetMapping("/pokus")
	public boolean conditionPok() {
		return condition.anyOfLinesSatisfies(line -> line.contains("ffmpeg version"));
	}
}