package pl.piotrfigura.reactive.s05;

import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class HandleUntilAssignment {

    public static void main(String[] args) {
        Flux.<String>generate(sink -> sink.next(Util.faker().country().name()))
            .handle((item, sink) -> {
                sink.next(item);
                if (item.equalsIgnoreCase("canada")) {
                    sink.complete();
                }
            })
            .subscribe(Util.subscriber());

    }

}
