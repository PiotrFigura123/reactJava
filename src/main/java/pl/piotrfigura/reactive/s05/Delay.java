package pl.piotrfigura.reactive.s05;

import java.time.Duration;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class Delay {

    public static void main(String[] args) {
        Flux.range(1,10)
            .log()
            .delayElements(Duration.ofSeconds(1))
            .subscribe(Util.subscriber());
        Util.sleepSeconds(12);
    }

}
