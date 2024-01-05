package cz.asterionsoft.ffmpegwrapper.service;

import cz.asterionsoft.ffmpegwrapper.store.DbEntry;
import cz.asterionsoft.ffmpegwrapper.store.InputDb;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final InputDb inputDb;

    @SneakyThrows
    public String copyFile(MultipartFile file) {
        log.debug("Copy file from temp to in directory.");
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + "." + getSuffix(file.getOriginalFilename());
        Path target = Paths.get("in", newName);
        file.transferTo(target);

        inputDb.add(new DbEntry(uuid, newName, file.getOriginalFilename()));
        return uuid;
    }

    public List<String> getFiles(String directory, String uuid) {
        log.debug("Get files from {} directory and uuid {}.", directory, uuid);
        return Arrays
                .asList(Objects.
                        requireNonNull(
                                Paths.get(directory)
                                        .toFile()
                                        .list((dir, name) -> name.contains(uuid))
                        )
                );
    }

    private String getSuffix(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }
}
