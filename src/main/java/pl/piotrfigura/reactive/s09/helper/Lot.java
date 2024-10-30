package pl.piotrfigura.reactive.s09.helper;

import java.time.Duration;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class Lot {
    public static final String AIRLINE = "Lot";
    public static Flux<Flight> getFlight(){
        return Flux.range(1, Util.faker().random().nextInt(2,10))
            .delayElements(Duration.ofMillis(Util.faker.random().nextInt(200,1000)))
            .map(i -> new Flight(AIRLINE, Util.faker.random().nextInt(300, 1000)))
            .transform(Util.fluxLogger(AIRLINE));
    }

}
