package cz.asterionsoft.ffmpegwrapper.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * probably can not be paralellized! needs to finish step by step!
 */
public class CmdExecutor {

	public void run(String command) {
		System.out.println("Running this: " + command);

		ProcessBuilder builder = new ProcessBuilder(command.split("\\s+"));
		builder.redirectErrorStream(true);

		try {
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println("problem executing: " + e.getMessage());
		}
	}
}
