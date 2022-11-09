package cz.asterionsoft.ffmpegwrapper.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasicIntegrationTest {

	@Test
	void shouldPrintVersion() {
		VideoBuilder videoBuilder = VideoBuilder.getInstance().version();
		Assertions.assertTrue(
				videoBuilder.getLastRunOutput().get(0).contains("ffmpeg version "),
				"should print version"
		);
	}
}
