package cz.asterionsoft.ffmpegwrapper.service;


import cz.asterionsoft.ffmpegwrapper.exception.NotImplYet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudioBuilder implements Processor {
	private final Context context;
	private final EasyWrapper wrapper;
	private MultimediaBuilder parent;

	@Override
	public MultimediaBuilder back() {
		return parent;
	}

	@Override
	public AudioBuilder setInput(MultimediaBuilder parent, String audioFile) {
		this.parent = parent;
		context.setInputAudioFileName(audioFile);
		return this;
	}
	@Override
	public Processor setOutput(String fileName) {
		context.setOutputAudioFileName(fileName);
		return this;
	}
	@Override
	public AudioBuilder markOutputAsInput() {
		context.setInputAudioFileName(context.getOutputAudioFileName());
		context.setOutputAudioFileName(null);
		return this;
	}

	@Override
	public Processor convert() {
		throw new NotImplYet();
	}

	@Override
	public Processor append(String secondFile, String outputFile) {
		throw new NotImplYet();
	}

	@Override
	public Processor trim(String fromTime, String toTime) {
		throw new NotImplYet();
	}

	@Override
	public Processor merge(String secondFile, String outputFile) {
		wrapper.mergeTwoAudios(
				context.getInputAudioFileName(),
				secondFile,
				outputFile
		);
		return this;
	}
}
