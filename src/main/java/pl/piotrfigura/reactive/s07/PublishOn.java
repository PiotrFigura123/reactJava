package pl.piotrfigura.reactive.s07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

class PublishOn {

    public static final Logger log = LoggerFactory.getLogger(PublishOn.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                log.info("generating: {}", i);
                sink.next(i);
            }
            sink.complete();
        })
            .publishOn(Schedulers.parallel())
            .doOnNext(v -> log.info("value: {}", v))
            .doFirst(()-> log.info("first1"))
            .publishOn(Schedulers.boundedElastic())
            .doFirst(()-> log.info("sec2"));

        Runnable runnable1 = () ->
        flux
            .subscribe(Util.subscriber("sub1"));

        new Thread(runnable1).start();
        Util.sleepSeconds(2);
    }

}
