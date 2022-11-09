package cz.asterionsoft.ffmpegwrapper;

import cz.asterionsoft.ffmpegwrapper.easy.VideoBuilder;

public class Main {
	public static void main(String[] args) {
		VideoBuilder.getInstance()
				.version()
				.inputVideo("aaa.mov")
				.outputVideo("bbb.mp4")
				.convert()
				.trim("", "");
				//.addTitles("");

	}

}