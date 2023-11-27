package cz.asterionsoft.ffmpegwrapper.to;

import java.util.List;

public record DefaultResponse(String message, String status, String uuid, List<String> files) {
}
