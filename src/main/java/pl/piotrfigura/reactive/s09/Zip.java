package pl.piotrfigura.reactive.s09;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class Zip {

    public static final Logger log = LoggerFactory.getLogger(Zip.class);

    record Car(String t1, String t2, String t3){};
    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTires())
            .map(t -> new Car(t.getT1(), t.getT2(), t.getT3()))
            .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
    //maximum records of min producer
    private static Flux<String> getBody(){
        return Flux.range(1,5)
            .map(i -> "body " + i)
            .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine(){
        return Flux.range(1,1)
            .map(i -> "engine " + i)
            .delayElements(Duration.ofMillis(200));
    }
    private static Flux<String> getTires(){
        return Flux.range(1,10)
            .map(i -> "tires " + i)
            .delayElements(Duration.ofMillis(75));
    }
}
