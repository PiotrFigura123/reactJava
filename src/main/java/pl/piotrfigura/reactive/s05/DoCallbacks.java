package pl.piotrfigura.reactive.s05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class DoCallbacks {

    private static final Logger log = LoggerFactory.getLogger(DoCallbacks.class);
    public static void main(String[] args) {
        Flux.<Integer>create(fluxSink -> {
            log.info("producer begind");
            for (int i = 0; i < 4; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
//            fluxSink.error(new RuntimeException("error"));
            log.info("producer ends");
        })
            .doOnComplete(() -> log.info("doOnComplete-1"))
            .doFirst(() -> log.info("doFirst-1"))  //before first time after initialization
            .doOnNext(item -> log.info("doOnNext-1: {}", item)) // when value is emitted
            .doOnSubscribe(subscription -> log.info("doOnSubscription-1: {}", subscription))
            .doOnRequest(request -> log.info("doOnRequest-1: {}", request))
            .doOnError(error -> log.info("doOnError-1: {}", error))
            .doOnTerminate(() -> log.info("doOnTerminated-1"))
            .doOnCancel(() -> log.info("doOnCancel-1"))
            .doOnDiscard(Object.class, o -> log.info("doOnDiscard-1: {}", o))
            .doFinally(signal -> log.info("doFinally -1: {}", signal))
            .take(20)
            .doOnComplete(() -> log.info("doOnComplete-2"))
            .doFirst(() -> log.info("doFirst-2"))  //before first time after initialization
            .doOnNext(item -> log.info("doOnNext-2: {}", item)) // when value is emitted
            .doOnSubscribe(subscription -> log.info("doOnSubscription-2: {}", subscription))
            .doOnRequest(request -> log.info("doOnRequest-2: {}", request))
            .doOnError(error -> log.info("doOnError-2: {}", error))
            .doOnTerminate(() -> log.info("doOnTerminated-2"))
            .doOnCancel(() -> log.info("doOnCancel-2"))
            .doOnDiscard(Object.class, o -> log.info("doOnDiscard-2: {}", o))
            .doFinally(signal -> log.info("doFinally -2: {}", signal))
            .take(4)
            .subscribe(Util.subscriber("subscriber"));
    }

}
