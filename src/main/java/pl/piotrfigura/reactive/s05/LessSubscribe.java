package pl.piotrfigura.reactive.s05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

class LessSubscribe {

    public static final Logger log = LoggerFactory.getLogger(LessSubscribe.class);
    public static void main(String[] args) {
        Flux.range(1,10)
            .doOnNext(i -> log.info("received: {}", i))
            .doOnComplete(() -> log.info("completed"))
            .doOnError(err -> log.info("error: {}", err))
            .subscribe();
    }

}
