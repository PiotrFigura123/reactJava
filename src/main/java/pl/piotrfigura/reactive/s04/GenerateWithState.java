package pl.piotrfigura.reactive.s04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class GenerateWithState {

    private static final Logger log = LoggerFactory.getLogger(FluxGenerate.class);

    public static void main(String[] args) {

        demo2();
    }

    private static void demo1(){
        Flux.generate(synchronousSink -> {
                var country = Util.faker().country().name();
                synchronousSink.next(country);
                if(country.equalsIgnoreCase("canada")){
                    synchronousSink.complete();
                }
            })
            .subscribe(Util.subscriber());
    }

    private static void demo2(){
        Flux.generate(
            ()-> 0,
            (counter, sink) -> {
                var country = Util.faker().country().name();
                sink.next(country);
                counter++;
                if(counter == 30 || country.equalsIgnoreCase("canada")){
                    sink.complete();
                }
                return counter;
            }
        ).subscribe(Util.subscriber());
    }

}
