package cz.asterionsoft.ffmpegwrapper.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class EasyWrapper {

    private static final String FFMPEG = "ffmpeg";
    private static final String PLACEHOLDER = "xxx";

    private final CmdExecutor executor;

    /**
     * Merge audio.
     * <p>
     * ffmpeg -i INPUT1 -i INPUT2 -i INPUT3 -filter_complex amix=inputs=3:duration=first:dropout_transition=3 OUTPUT
     */
    public void mergeTwoAudios(String audioFile1, String audioFile2, String outputAudio) {
        commonRun(
                "ffmpeg -i xxx -i xxx -filter_complex amix=inputs=2:duration=first xxx",
                audioFile1,
                audioFile2,
                outputAudio
        );
    }

    /**
     * Extracts whole audio from vide:
     * ffmpeg -i sample.avi -q:a 0 -map a sample.mp3
     * extract only trimmed part:
     * ffmpeg -i sample.avi -ss 00:03:05 -t 00:00:45.0 -q:a 0 -map a sample.mp3
     * extraction accrding to best answer:
     * ffmpeg -i input-video.avi -vn -acodec copy output-audio.aac
     *
     * @param inputVideoPath input video
     * @param outputAudio    output audio
     */
    public void extractAudio(String inputVideoPath, String outputAudio) {
        commonRun(
                "ffmpeg -i xxx -q:a 0 -map a xxx",
                inputVideoPath,
                outputAudio
        );
    }

    /**
     * ffmpeg -i in.mp4 img%04d.png
     * <p>
     * ffmpeg -i in.mp4 -vf "fps=1" img%04d.png
     * Potential conflict when using % in name.
     *
     * @param inputVideoPath
     * @param picNameBase
     */
    public void splitVideoToPictureSeries(String inputVideoPath, String picNameBase) {
        commonRun(
                "ffmpeg -i xxx -vf fps=1 " + picNameBase + "%04d.png",
                inputVideoPath
        );
    }


    /**
     * ffmpeg -i video.mp4 -i audio.wav -map 0:v -map 1:a -c:v copy -shortest output.mp4
     * ffmpeg -i video.mp4 -i audio.wav -map 0:v -map 1:a -c:v copy output.mp4
     *
     * @param inputVideoPath
     * @param inputAudioPath
     * @param outputVideoPath
     */
    public void replaceAudioInVideo(String inputVideoPath, String inputAudioPath, String outputVideoPath) {
        commonRun(
                "ffmpeg -i xxx -i xxx -map 0:v -map 1:a -c:v copy xxx",
                inputVideoPath,
                inputAudioPath,
                outputVideoPath
        );
    }

    /**
     * ffmpeg -framerate 1 -i happy%d.jpg -c:v libx264 -r 30 output.mp4
     * -r framerate of output video
     * -framerate ... framerate of pictures
     *
     * @param durationSeconds  duration of each image in video
     * @param fileNameNumbered e.g. happy%d.jpg
     */
    public void makeVideoFromPictureSeries(String durationSeconds, String fileNameNumbered, String outputVideo) {
        commonRun("ffmpeg -framerate " + durationSeconds
                + " -i " + fileNameNumbered
                + " -c:v libx264" + " "
                + outputVideo);
    }

    /**
     * "ffmpeg -i input.mp4 -ss 00:05:20 -t 00:10:00 -c:v copy -c:a copy output1.mp4"
     *
     * @param input
     * @param output
     * @param fromTime
     * @param duration
     */
    public void trim(String input, String output, String fromTime, String duration) {
        commonRun(
                "ffmpeg -i xxx -ss xxx -t xxx -c:v copy -c:a copy xxx",
                input,
                fromTime,
                duration,
                output
        );
    }

    public void convert(String input, String output) {
        commonRun("ffmpeg -i xxx xxx", input, output);
    }

    /**
     * ffmpeg -i input.mp4 -i subtitle.en.srt -c copy -c:s mov_text -metadata:s:s:0 language=eng ouptut_english.mp4
     *
     * @param input
     * @param output
     * @param srtFile
     * @param lang
     */
    public void softTitles(String input, String output, String srtFile, String lang) {
        commonRun("ffmpeg -i xxx -i xxx -c copy -c:s mov_text -metadata:s:s:0 language=xxx xxx",
                input, srtFile, lang, output);
    }

    /**
     * ffmpeg -i input.mp4 -vf subtitles=subtitle.srt output_srt.mp4
     *
     * @param input
     * @param output
     * @param srtFile
     */
    public void hardTitles(String input, String output, String srtFile) {
        commonRun("ffmpeg -i xxx -vf subtitles=xxx xxx",
                input, srtFile, output);
    }

    public void version() {
        commonRun("ffmpeg -version");
    }

    private void commonRun(String template, String... paramsInCorrectOrder) {
        String command = replacePlaceholder(template, paramsInCorrectOrder);
        executor.run(command);
    }

    String getLastRunOutputHTML() {
        return executor
                .getLastRunOutput()
                .stream()
                .map(s -> s + "<br/>")
                .reduce("", String::concat);
    }

    private String replacePlaceholder(String template, String... paramsInCorrectOrder) {
        String command = template;
        if (paramsInCorrectOrder == null) {
            return command;
        }
        for (String param : paramsInCorrectOrder) {
            command = template.replaceFirst(PLACEHOLDER, param);
        }
        return command;
    }
}
