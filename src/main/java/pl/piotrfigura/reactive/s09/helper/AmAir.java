package pl.piotrfigura.reactive.s09.helper;

import java.time.Duration;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class AmAir {
    public static final String AIRLINE = "AmAir";
    public static Flux<Flight> getFlight(){
        return Flux.range(1, Util.faker().random().nextInt(5,10))
            .delayElements(Duration.ofMillis(Util.faker.random().nextInt(200,400)))
            .map(i -> new Flight(AIRLINE, Util.faker.random().nextInt(300, 1200)))
            .transform(Util.fluxLogger(AIRLINE));
    }

}
