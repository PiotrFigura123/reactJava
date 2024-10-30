package pl.piotrfigura.reactive.s05;

import java.time.Duration;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Mono;

class Timeout {

    public static void main(String[] args) {

        var mono = getProductName()
            .timeout(Duration.ofSeconds(2), fallback());

        mono
            .timeout(Duration.ofMillis(200))
            .subscribe(Util.subscriber());
        Util.sleepSeconds(4);
    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "service " + Util.faker().commerce().productName())
            .delayElement(Duration.ofMillis(1800));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> "fallback " + Util.faker().commerce().productName())
            .delayElement(Duration.ofMillis(300));
    }

}
