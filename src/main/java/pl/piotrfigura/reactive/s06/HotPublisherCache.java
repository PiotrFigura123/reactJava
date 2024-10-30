package pl.piotrfigura.reactive.s06;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class HotPublisherCache {
    public static final Logger log = LoggerFactory.getLogger(HotPublisherCache.class);

    public static void main(String[] args) {
        var stockFelux = stockStream().replay().autoConnect(0);
        //po dolaczeniu ktos dostaje aktualna wartosc
        Util.sleepSeconds(4);

        log.info("piotr joining");
        stockFelux
            .subscribe(Util.subscriber("piotr"));
        Util.sleepSeconds(4);

        log.info("ania joining");
        stockFelux
            .subscribe(Util.subscriber("ania"));
        Util.sleepSeconds(15);
    }

    private static Flux<Integer> stockStream(){
        return Flux.generate(
            sink -> sink.next(Util.faker.random().nextInt(10, 100)))
            .delayElements(Duration.ofSeconds(3))
            .doOnNext(price -> log.info("emmitine price : {}", price))
            .cast(Integer.class);
    }
}
