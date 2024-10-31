package pl.piotrfigura.reactive;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Mono;

class Retry {

    public static final Logger log = LoggerFactory.getLogger(Retry.class);

    public static void main(String[] args) {
        demo2();
        Util.sleepSeconds(10);
    }

    private static void demo1() {
        getProductName()
            .retry()
            .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getProductName()
            .retryWhen(
                reactor.util.retry.Retry.fixedDelay(1, Duration.ofSeconds(1))
//                    .doBeforeRetry(rs -> log.info("retrying"))
                    .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                    .onRetryExhaustedThrow((spec, signal) -> signal.failure())
            )
            .subscribe(Util.subscriber());
    }


    private static Mono<String> getProductName() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                if (atomicInteger.incrementAndGet() < 3) {
                    throw new RuntimeException("ups");
                }
                return Util.faker.country().name();
            })
            .doOnError(err -> log.info("ERROR: {}", err))
            .doOnSubscribe(s -> log.info("subbscribing"));
    }

}
