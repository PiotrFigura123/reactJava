package pl.piotrfigura.reactive.s04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(FluxGenerate.class);

    public static void main(String[] args) {

        demo2();
    }

    private static void demo1(){
        Flux.generate(synchronousSink -> {
                log.info("invoked");
                var country = Util.faker().country().name();
                synchronousSink.next(country);
                if(country.equalsIgnoreCase("canada")){
                    synchronousSink.complete();
                }
            })
            .subscribe(Util.subscriber());
    }

    private static void demo2(){
        Flux.<String>generate(synchronousSink -> {
                var country = Util.faker().country().name();
                synchronousSink.next(country);
            })
            .takeUntil(c -> c.equalsIgnoreCase("canada"))
            .subscribe(Util.subscriber());
    }
}
