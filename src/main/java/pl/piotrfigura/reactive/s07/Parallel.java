package pl.piotrfigura.reactive.s07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

class Parallel {

    public static final Logger log = LoggerFactory.getLogger(PublishOnSubscribeOn.class);

    public static void main(String[] args) {
        Flux.range(1,10)
            .parallel(3)
            .runOn(Schedulers.parallel())
            .map(Parallel::process)
            .sequential()
            .map(i -> i + "a")
            .subscribe(Util.subscriber());

    Util.sleepSeconds(3);
    }

    private static int process(int i){
        log.info("time consumig task{}", i);
        Util.sleepSeconds(1);
        return i*2;
    }
}

