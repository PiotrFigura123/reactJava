package pl.piotrfigura.reactive.s05;

import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class SwitchIfEmpty {

    public static void main(String[] args) {

        Flux.range(1,10)
            .filter(i -> i > 5)
            .switchIfEmpty(fallback())
            .subscribe(Util.subscriber());
    }

    private static Flux<Integer> fallback(){
        return Flux.range(100,3);
    }
}
