package pl.piotrfigura.reactive.common;

import com.github.javafaker.Faker;
import java.time.Duration;
import java.util.function.UnaryOperator;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.s09.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Util {

    public static final Logger log = LoggerFactory.getLogger(Util.class);

    public static final Faker faker = Faker.instance();
    public static <T>Subscriber<T> subscriber(){
        return new DefaultSubscriberImpl<>("");
    }
    public static <T>Subscriber<T> subscriber(String name){
        return new DefaultSubscriberImpl<>(name);
    }

    public static Faker faker(){
        return faker;
    }

    public static void sleepSeconds(int seconds){
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name){
        return flux -> flux
            .doOnSubscribe(s -> log.info("subscribing to {}", name))
            .doOnCancel(()-> log.info("cancelling {}", name))
            .doOnComplete(() -> log.info("{} completed", name));
    }
}
