package pl.piotrfigura.reactive.s05;

import java.util.Optional;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class DefaultEmpty {

    public static void main(String[] args) {

        Mono.empty()
            .defaultIfEmpty("fallback")
            .subscribe(Util.subscriber());

        Mono.just(1)
            .defaultIfEmpty(-1)
            .subscribe(Util.subscriber());

        Flux.range(1,10)
            .filter(i -> i > 10)
            .defaultIfEmpty(50)
            .subscribe(Util.subscriber());


    }

}
