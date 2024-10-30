package pl.piotrfigura.reactive.s05;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class Transform {

    public static final Logger log = LoggerFactory.getLogger(Transform.class);

    record Customer(int id, String name) {

    }

    record PurchaseOrder(String productName, int price, int quantity) {

    }

    public static void main(String[] args) {

        var isDebugEnable = true;

        getCustomer()
            .doOnNext(i -> log.info("received: {}", i))
            .doOnComplete(() -> log.info("completed"))
            .doOnError(err -> log.info("error: {}", err))
            .subscribe();
        getPurchaseOrder()
            .transform(isDebugEnable ? addDebugger() : Function.identity())
            .subscribe();
    }

    private static Flux<Customer> getCustomer() {
        return Flux.range(1, 3)
            .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrder() {
        return Flux.range(1, 3)
            .map(i -> new PurchaseOrder(Util.faker().commerce().productName(), i, i));
    }

    private static <T>UnaryOperator<Flux<T>> addDebugger(){
        return flux -> flux
            .doOnNext(i -> log.info("received: {}", i))
            .doOnComplete(() -> log.info("completed"))
            .doOnError(err -> log.info("error: {}", err));
    }
}
