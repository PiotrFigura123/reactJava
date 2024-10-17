package pl.piotrfigura.reactive.s04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s01.subscriber.SubscriberImpl;
import reactor.core.publisher.Flux;

class FluxCreateDownstreamDemand {

    public static final Logger log = LoggerFactory.getLogger(FluxSinkThreadSafety.class);

    public static void main(String[] args) {
        produceOnDemand();
    }

    private static void produceEarly() {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                log.info("generated: {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
    }

    private static void produceOnDemand(){
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(request -> {
                for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                    var name = Util.faker().name().firstName();
                    log.info("generated: {}", name);
                    fluxSink.next(name);
                }
            });

        }).subscribe(subscriber);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
    }
}
