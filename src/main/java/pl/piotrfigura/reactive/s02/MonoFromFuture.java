package pl.piotrfigura.reactive.s02;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Mono;

class MonoFromFuture {

    public static final Logger log = LoggerFactory.getLogger(MonoFromCallable.class);

    public static void main(String[] args) {

        Mono.fromFuture(()-> getName())
            .subscribe(Util.subscriber("subscriber"));
        Util.sleepSeconds(1);
    }
    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()-> {
            log.info("generating name");
            return Util.faker().name().firstName();
        });
    }
}
