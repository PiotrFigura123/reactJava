package pl.piotrfigura.reactive.s04;

import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class FluxCreate {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            String country;
            do{
                country = Util.faker().country().name();
                fluxSink.next(country);
            }while (!country.equalsIgnoreCase("canada"));
            fluxSink.complete();
        })
            .subscribe(Util.subscriber());
    }

}
