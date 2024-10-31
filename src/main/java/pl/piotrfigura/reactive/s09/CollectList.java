package pl.piotrfigura.reactive.s09;

import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class CollectList {

    public static void main(String[] args) {

        Flux.range(1, 10)
//            .concatWith(Mono.error(new RuntimeException("ups")))
            .collectList()
            .subscribe(Util.subscriber());
    }
}
