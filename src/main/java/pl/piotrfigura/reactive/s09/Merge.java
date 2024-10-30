package pl.piotrfigura.reactive.s09;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s09.helper.NameGenerator;
import reactor.core.publisher.Flux;

class Merge {


    public static final Logger log = LoggerFactory.getLogger(NameGenerator.class);


    public static void main(String[] args) {

        Util.sleepSeconds(3);
    }

    private static void demo1() {
        Flux.merge(producer1(), producer2(), producer3())
            .take(2)
            .subscribe(Util.subscriber());

    }

    private static void demo2() {
        producer1()
            .mergeWith(producer2())
            .mergeWith(producer3())
            .subscribe(Util.subscriber());

    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
            .transform(Util.fluxLogger("producer1"))
            .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(-2)
            .transform(Util.fluxLogger("producer2"))
            .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.just(11, 12, 3)
            .transform(Util.fluxLogger("producer3"))
            .delayElements(Duration.ofMillis(10));
    }
}
