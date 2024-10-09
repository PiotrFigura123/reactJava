package pl.piotrfigura.reactive.s02;


import pl.piotrfigura.reactive.s01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

class MonoJust {

    public static void main(String[] args) {
        var mono = Mono.just("vins");
        var subscriber = new SubscriberImpl();

        mono.subscribe(subscriber);

        subscriber.getSubscription().request(10);
        //te nie maja efektu bo jest tylko 1 rekord
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();
    }
}
