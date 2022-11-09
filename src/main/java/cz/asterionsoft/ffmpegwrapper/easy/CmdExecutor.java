package cz.asterionsoft.ffmpegwrapper.easy;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * probably can not be parallelized! needs to finish step by step!
 */
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
			}
		} catch (IOException e) {
			System.out.println("problem executing: " + e.getMessage());
		}
	}

	List<String> getLastRunOutput() {
		return lastRunOutput;
	}
}
