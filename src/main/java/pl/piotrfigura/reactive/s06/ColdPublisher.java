package pl.piotrfigura.reactive.s06;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class ColdPublisher {

    public static final Logger log = LoggerFactory.getLogger(ColdPublisher.class);

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        var flux = Flux.create(fluxSink -> {
            log.info("invoked");
            for (int i = 0; i < 3; i++) {
                fluxSink.next(atomicInteger.incrementAndGet());
            }

        });
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));


    }

}
