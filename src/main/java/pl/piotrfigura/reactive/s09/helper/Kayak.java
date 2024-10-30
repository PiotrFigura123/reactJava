package pl.piotrfigura.reactive.s09.helper;

import java.time.Duration;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

public class Kayak {

    public static Flux<Flight> getFlight(){
        return Flux.merge(AmAir.getFlight(),
            Qatar.getFlight(),
            Lot.getFlight()
        )
            .take(Duration.ofSeconds(2));
    }

}
