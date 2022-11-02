package cz.asterionsoft.ffmpegwrapper;

import cz.asterionsoft.ffmpegwrapper.easy.EasyWrapper;

public class Main {
	public static void main(String[] args) {
		EasyWrapper wrapper = new EasyWrapper();
		wrapper.version();
		//wrapper.convert("aaa.mov", "bbb.mp4");

		//File d = new File("/Users/janmarcis/source/ffmpeg-java-wrapper");
		//System.out.println(d.exists());

	}
}