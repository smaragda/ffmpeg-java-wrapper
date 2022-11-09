package cz.asterionsoft.ffmpegwrapper.easy;

import lombok.Data;

@Data
public class Context {

	// ------------- BASIC NEEDED -------------
	private String inputVideoFileName;
	private String outputVideoFileName;

	// ------------- BASED ON ACTIONS -------------
	private String fromTime;
	private String toTime;
}
