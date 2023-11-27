package cz.asterionsoft.ffmpegwrapper.controller;

import cz.asterionsoft.ffmpegwrapper.service.FileService;
import cz.asterionsoft.ffmpegwrapper.service.MultimediaBuilder;
import cz.asterionsoft.ffmpegwrapper.store.DbEntry;
import cz.asterionsoft.ffmpegwrapper.store.InputDb;
import cz.asterionsoft.ffmpegwrapper.to.DefaultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MultimediaBuilder builder;
    private final FileService fileService;
    private final InputDb inputDb;

    @GetMapping(value = "/version", produces = "text/html")
    public String version() {
        return builder
                .version()
                .getLastRunOutput();
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<DefaultResponse> upload(@RequestParam("file") MultipartFile file) {
        String uuid = fileService.copyFile(file);

        return ResponseEntity
                .ok()
                .body(new DefaultResponse(
                                "File " + file.getOriginalFilename() + " uploaded successfully",
                                "OK",
                                uuid,
                                fileService.getFiles("in", uuid)
                        )
                );
    }

    @PostMapping(value = "/pics/{uuid}")
    public ResponseEntity<DefaultResponse> pics(@PathVariable String uuid) {
        DbEntry dbEntry = inputDb.get(uuid);
        String output = builder
                .useVideo("in/" + dbEntry.originalName())
                .splitVideoInPictureSeries("out/" + uuid + "-pic")
                .back()
                .getLastRunOutput();

        return ResponseEntity
                .ok()
                .body(
                        new DefaultResponse(
                                output,
                                "OK",
                                uuid,
                                fileService.getFiles("out", uuid))
                );
    }
}
