package pl.piotrfigura.reactive.s05;

import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class Handle {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1, 10);
        flux
            .filter(i -> i != 7)
            .handle((item, sink) -> {
                switch (item) {
                    case 1 -> sink.next(-2);
                    case 4 -> {
                    }
                    case 7 -> sink.error(new RuntimeException("error"));
                    default -> sink.next(item);
                }
            })
            .subscribe(Util.subscriber());

    }

}
