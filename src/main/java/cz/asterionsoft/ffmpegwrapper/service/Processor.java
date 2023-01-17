package cz.asterionsoft.ffmpegwrapper.service;

public interface Processor {
	MultimediaBuilder back();

	Processor setInput(MultimediaBuilder parent, String fileName);

	Processor setOutput(String fileName);

	Processor markOutputAsInput();

	Processor convert();

	Processor trim(String fromTime, String toTime);

	Processor merge(String secondFile, String outputFile);

	Processor append(String secondFile, String outputFile);
}
