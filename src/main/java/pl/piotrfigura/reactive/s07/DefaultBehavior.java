package pl.piotrfigura.reactive.s07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class DefaultBehavior {

    public static final Logger log = LoggerFactory.getLogger(DefaultBehavior.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                log.info("generating: {}", i);
                sink.next(i);
            }
            sink.complete();
        })
            .doOnNext(v -> log.info("value: {}", v));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));
        new Thread(runnable).start();
    }

}
