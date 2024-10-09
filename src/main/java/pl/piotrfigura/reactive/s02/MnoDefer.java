package pl.piotrfigura.reactive.s02;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Mono;

class MnoDefer {

    public static final Logger log = LoggerFactory.getLogger(MonoFromCallable.class);

    public static void main(String[] args) {

        Mono.defer(()-> createPublisher()).subscribe(Util.subscriber());
        //defer delay creation of publisher if required
    }

    private static Mono<Integer> createPublisher(){
        log.info("createing publisher");
        var list = List.of(1,2,3);
        Util.sleepSeconds(3);
        return Mono.fromSupplier(()->sum(list));
    }

    //time consumig business loigic
    private static int sum(List<Integer> list){
        log.info("finding the sum of {}", list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(a -> a).sum();
    }

}
