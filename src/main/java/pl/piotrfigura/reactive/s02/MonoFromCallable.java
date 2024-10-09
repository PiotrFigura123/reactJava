package pl.piotrfigura.reactive.s02;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Mono;

class MonoFromCallable {
    public static final Logger log = LoggerFactory.getLogger(MonoFromCallable.class);

    public static void main(String[] args) {

        var list = List.of(1, 2,3);
        Mono.fromCallable(() -> sum(list))
            .subscribe(Util.subscriber("subscriber"));
    }

    private static int sum(List<Integer> list) throws Exception{
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }
    //supplier and Callable are functional interfaces,
    // Supplier part of java 8 -> does not have the exceptional as part of the method signature.
    // It can throw RuntimeException but not have checked exception
    // Collable have the throws exceptions
}
