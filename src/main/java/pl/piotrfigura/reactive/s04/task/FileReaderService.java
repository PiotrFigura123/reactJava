package pl.piotrfigura.reactive.s04.task;

import java.nio.file.Path;
import reactor.core.publisher.Flux;

interface FileReaderService {

    Flux<String> read(Path path);
}
