package pl.piotrfigura.reactive.s06;

import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s04.helper.NameGenerator;
import reactor.core.publisher.Flux;

class FluxCreateRefactorIssueFix {

    public static void main(String[] args) {
        var generator = new NameGenerator();
        var flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i<10;i++){
            generator.generate();
        }
    }

}
