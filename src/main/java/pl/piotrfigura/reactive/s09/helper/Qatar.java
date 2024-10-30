package pl.piotrfigura.reactive.s09.helper;

import java.time.Duration;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class Qatar {
    public static final String AIRLINE = "Qatar";
    public static Flux<Flight> getFlight(){
        return Flux.range(1, Util.faker().random().nextInt(3,5))
            .delayElements(Duration.ofMillis(Util.faker.random().nextInt(300,800)))
            .map(i -> new Flight(AIRLINE, Util.faker.random().nextInt(400, 900)))
            .transform(Util.fluxLogger(AIRLINE));
    }

}
