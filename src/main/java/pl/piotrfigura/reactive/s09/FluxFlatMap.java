package pl.piotrfigura.reactive.s09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s09.app.Order;
import pl.piotrfigura.reactive.s09.app.OrderService;
import pl.piotrfigura.reactive.s09.app.PaymentService;
import pl.piotrfigura.reactive.s09.app.User;
import pl.piotrfigura.reactive.s09.app.UserService;

class FluxFlatMap {

    public static final Logger log = LoggerFactory.getLogger(FluxFlatMap.class);

    public static void main(String[] args) {


        UserService.getAllUsers()
            .map(User::id)
            .flatMap(OrderService::getUserOrders)
            .subscribe(Util.subscriber());

        Util.sleepSeconds(4);
    }

}
