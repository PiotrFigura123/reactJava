package pl.piotrfigura.reactive.s09.helper;

import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

public class NameGenerator {

    public static final Logger log = LoggerFactory.getLogger(NameGenerator.class);

    private final List<String> redis = new ArrayList<>();
    public Flux<String> generateName() {
        return Flux.generate(sink -> {
            log.info("generating name");
            Util.sleepSeconds(1);
            var name = Util.faker.name().firstName();
            redis.add(name);
            sink.next(name);
        })
            .startWith(redis)
            .cast(String.class);

    }
}
