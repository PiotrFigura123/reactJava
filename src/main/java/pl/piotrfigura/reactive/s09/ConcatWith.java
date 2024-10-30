package pl.piotrfigura.reactive.s09;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s09.helper.NameGenerator;
import reactor.core.publisher.Flux;

class ConcatWith {


    public static final Logger log = LoggerFactory.getLogger(NameGenerator.class);


    public static void main(String[] args) {

        demo3();
        Util.sleepSeconds(3);
    }

    private static void demo1() {
        producer1()
            .concatWith(producer2())
            .subscribe(Util.subscriber());
    }

    private static void demo3() {
        Flux.concat(producer2(), producer1())
            .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
            .doOnSubscribe(s -> log.info("subscribing to producer1"))
            .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2(){
        return Flux.just(-2)
            .doOnSubscribe(s -> log.info("subscribing to producer2"))
            .delayElements(Duration.ofMillis(10));
    }
}
