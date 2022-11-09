package cz.asterionsoft.ffmpegwrapper.service;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * TODO should be request scope or smtg similar
 */
@Data
@Component
public class Context {

	// ------------- BASIC NEEDED -------------
	private String inputVideoFileName;
	private String outputVideoFileName;

	// ------------- BASED ON ACTIONS -------------
	private String fromTime;
	private String toTime;
}
