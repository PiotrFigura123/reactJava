package pl.piotrfigura.reactive.s10;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class Repeat {

    public static void main(String[] args) {

        Flux.just(1,2,3)
                .repeat(3)
                    .subscribe(Util.subscriber());
        demo4();
        Util.sleepSeconds(10);
    }

    private static void demo1() {
        getProductName().repeat(3).subscribe(Util.subscriber());
    }

    private static void demo2() {
        getProductName().repeat()
            .takeUntil(c -> c.equalsIgnoreCase("canada"))
            .subscribe(Util.subscriber());
    }

    private static void demo3() {
        var atomicInteger = new AtomicInteger(0);
        getProductName()
            .repeat(() -> atomicInteger.incrementAndGet() < 3)
            .subscribe(Util.subscriber());
    }

    private static void demo4() {
        getProductName()
            .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(2)).take(2))
            .subscribe(Util.subscriber());
    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> Util.faker().country().name());
    }

}
