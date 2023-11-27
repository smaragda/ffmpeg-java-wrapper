package cz.asterionsoft.ffmpegwrapper.store;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InputDb {
    private static final int MAX_ENTRIES = 10;
    private final List<DbEntry> list = new CopyOnWriteArrayList<>();

    public void add(DbEntry entry) {
        if (list.size() >= MAX_ENTRIES) {
            list.remove(0);
        }
        list.add(entry);
    }

    public DbEntry get(String uuid) {
        return list.stream()
                .filter(e -> e.uuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

}
