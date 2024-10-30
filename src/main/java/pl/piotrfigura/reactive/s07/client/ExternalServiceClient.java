package pl.piotrfigura.reactive.s07.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.AbstractHttpClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    public Mono<String> getProductName(int productId){
        return this.httpClient.get()
            .uri("/demo01/product/" + productId)
            .responseContent()
            .asString()
            .doOnNext(m -> log.info("next: {}", m))
            .next()
            .publishOn(Schedulers.boundedElastic());
    }
}