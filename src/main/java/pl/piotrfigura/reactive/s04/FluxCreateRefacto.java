package pl.piotrfigura.reactive.s04;

import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s04.helper.NameGenerator;
import reactor.core.publisher.Flux;

class FluxCreateRefacto {

    public static void main(String[] args) {
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber());

        for (int i = 0; i<10;i++){
            generator.generate();
        }
    }

}
