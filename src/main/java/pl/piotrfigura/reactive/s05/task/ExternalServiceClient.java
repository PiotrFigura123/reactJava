package pl.piotrfigura.reactive.s05.task;

import java.time.Duration;
import pl.piotrfigura.reactive.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId){
        var defaultPath = "/demo03/product/" + productId;
        var timeoutPath = "/demo03/timeout-fallback/product/" + productId;
        var emptytPath = "/demo03/empty-fallback/product/" + productId;
        return getProductName(defaultPath)
            .timeout(Duration.ofSeconds(2), getProductName(timeoutPath))
            .switchIfEmpty(getProductName(emptytPath));

    }

    private Mono<String> getProductName(String path){
        return this.httpClient.get()
            .uri(path)
            .responseContent()
            .asString()
            .next();
    }
}