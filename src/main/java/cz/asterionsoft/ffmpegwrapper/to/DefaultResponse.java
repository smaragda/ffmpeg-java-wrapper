package cz.asterionsoft.ffmpegwrapper.to;

import java.util.Set;

public record DefaultResponse(String message, String status, String uuid, Set<String> files) {
}
