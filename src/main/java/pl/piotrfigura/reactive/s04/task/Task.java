package pl.piotrfigura.reactive.s04.task;

import java.nio.file.Path;
import pl.piotrfigura.reactive.common.Util;

class Task {

    public static void main(String[] args) {
        var path = Path.of("src/main/resources/s04/file.txt");
        var fileReaderService = new FileReaderServiceImpl();
        fileReaderService.read(path)
//            .take(2)
            .takeUntil(s -> s.equalsIgnoreCase("line2"))
            .subscribe(Util.subscriber());
    }

}
