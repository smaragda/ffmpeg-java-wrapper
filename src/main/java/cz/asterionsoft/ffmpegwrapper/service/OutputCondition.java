package cz.asterionsoft.ffmpegwrapper.service;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class OutputCondition {

	private final CmdExecutor executor;

	public OutputCondition(CmdExecutor executor) {
		this.executor = executor;
	}

	public boolean anyOfLinesSatisfies(Predicate<String> predicate) {
		return executor.getLastRunOutput().stream().anyMatch(predicate);
	}
}
