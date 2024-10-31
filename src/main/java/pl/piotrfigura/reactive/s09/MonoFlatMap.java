package pl.piotrfigura.reactive.s09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s09.app.OrderService;
import pl.piotrfigura.reactive.s09.app.PaymentService;
import pl.piotrfigura.reactive.s09.app.UserService;
import reactor.core.publisher.Mono;

class MonoFlatMap {

    public static final Logger log = LoggerFactory.getLogger(MonoFlatMap.class);

    public static void main(String[] args) {

        UserService.getUserId("jake")
                .flatMap(PaymentService::getUserBalance)
                    .subscribe(Util.subscriber());

        UserService.getUserId("jake")
                .flatMapMany(OrderService::getUserOrders)
                    .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }

}
