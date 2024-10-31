package pl.piotrfigura.reactive.s09;

import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s09.task.ExternalServiceClient;
import reactor.core.publisher.Flux;

class FluxFlatMapTask {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        Flux.range(1,10)
            .flatMap(client::getProduct)
            .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }
}
