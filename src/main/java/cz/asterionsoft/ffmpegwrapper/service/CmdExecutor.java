package cz.asterionsoft.ffmpegwrapper.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * probably can not be parallelized! needs to finish step by step!
 */
@Slf4j
@Component
class CmdExecutor {
	private final List<String> lastRunOutput = new ArrayList<>();

	public void run(String command) {
		System.out.println("Running this: " + command);
		lastRunOutput.clear();

		ProcessBuilder builder = new ProcessBuilder(command.split("\\s+"));
		builder.redirectErrorStream(true);

		try {
			Process p = builder.start();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = bufferedReader.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
				lastRunOutput.add(line);
				validate(line);
			}
		} catch (IOException e) {
			log.error("problem executing: " + e.getMessage());
		}
	}

	List<String> getLastRunOutput() {
		return lastRunOutput;
	}

	private void validate(String line) throws IOException {
		if (new OutputCondition(this).anyOfLinesSatisfies(s -> s.contains("No such file or directory"))) {
			throw new IOException("Problem found in ffmpeg output: " + line);
		}
	}
}
