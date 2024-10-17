package pl.piotrfigura.reactive.s04;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s04.helper.NameGenerator;
import reactor.core.publisher.Flux;

class FluxSinkThreadSafety {

    public static final Logger log = LoggerFactory.getLogger(FluxSinkThreadSafety.class);
    public static void main(String[] args) {
        demo2();
    }
    private static void demo1(){
        var list = new ArrayList<Integer>();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());

    }
    private static void demo2(){
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }
}
