package pl.piotrfigura.reactive.s02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Mono;

class MonoFromRunnable {

    public static final Logger log = LoggerFactory.getLogger(MonoFromCallable.class);

    public static void main(String[] args) {
        getProductName(1)
            .subscribe(Util.subscriber());
        getProductName(2)
            .subscribe(Util.subscriber());
    }

    private static Mono<String> getProductName(int productId){
        if(productId == 1){
            return Mono.fromSupplier(()-> Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(()-> notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId){
        log.info("notify business on unvailable product {}", productId);
    }
}
