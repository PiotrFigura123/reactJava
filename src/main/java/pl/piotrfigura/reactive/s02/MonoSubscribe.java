package pl.piotrfigura.reactive.s02;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

class MonoSubscribe {

    public static final Logger log = LoggerFactory.getLogger(MonoSubscribe.class);

    public static void main(String[] args) {
        var mono = Mono.just(1)
//            .map(i -> i / 0);
            .map(i -> i + "a");

        mono.subscribe(i -> log.info("received: {}", i),
            err -> log.error("error: ", err),
            () -> log.info("completed!"),
            subscription -> subscription.request(1));
    }
}
