package pl.piotrfigura.reactive.s06;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class PublisherAutoConnect {
    public static final Logger log = LoggerFactory.getLogger(PublisherAutoConnect.class);

    public static void main(String[] args) {
        var movieFlux = movieCreator().publish().autoConnect();
        //film dalej leci, kazdy moze sie dolaczyc
        //jak wrzuce autoConnect(0) to nie czeka az ktos sie podlaczy tylko leci
        Util.sleepSeconds(2);
        movieFlux
            .take(4)
            .subscribe(Util.subscriber("piotr"));
        Util.sleepSeconds(3);
        movieFlux
            .take(3)
            .subscribe(Util.subscriber("ania"));
        Util.sleepSeconds(15);
    }

    private static Flux<String> movieCreator(){
        return Flux.generate(
            () -> {
                log.info("recieved the request");
                return 1;
            },
            (state, sink) -> {
                var scene = "movie scene " + state;
                log.info("playing {}", scene);
                sink.next(scene);
                return ++state;
            }
        )
            .take(10)
            .delayElements(Duration.ofSeconds(1))
            .cast(String.class);
    }
}
