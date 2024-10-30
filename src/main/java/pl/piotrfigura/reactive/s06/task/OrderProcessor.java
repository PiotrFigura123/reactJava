package pl.piotrfigura.reactive.s06.task;

import reactor.core.publisher.Flux;

public interface OrderProcessor {
    void consume(Order order);

    Flux<String> stream();
}
