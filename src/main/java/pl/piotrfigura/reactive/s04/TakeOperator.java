package pl.piotrfigura.reactive.s04;

import java.util.stream.IntStream;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class TakeOperator {

    public static void main(String[] args) {
//        IntStream.range(1,10)
//            .limit(3)
//            .forEach(System.out::println);


        takeUntil();

    }

    private static void take(){
        Flux.range(1,10)
            .log("take")
            .take(30)
            .log("sub")
            .subscribe(Util.subscriber());
    }
    private static void takeWhile(){
        Flux.range(1,10)
            .log("take")
            .takeWhile(i -> i < 5 )
            .log("sub")
            .subscribe(Util.subscriber());
    }

    private static void takeUntil(){
        Flux.range(1,10)
            .log("take")
            .takeUntil(i -> i < 5 ) //stop when condition is met + allow last item
            .log("sub")
            .subscribe(Util.subscriber());
    }
}
