package pl.piotrfigura.reactive.s09.app;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

public class OrderService {

    public static final Map<Integer, List<Order>> orderTable = Map.of(
        1, List.of(
            new Order(1, Util.faker().commerce().productName(), Util.faker.random().nextInt(10,100)),
            new Order(1, Util.faker().commerce().productName(), Util.faker.random().nextInt(10,100))
        ),
        2, List.of(
            new Order(2, Util.faker().commerce().productName(), Util.faker.random().nextInt(10,100)),
            new Order(2, Util.faker().commerce().productName(), Util.faker.random().nextInt(10,100)),
            new Order(2, Util.faker().commerce().productName(), Util.faker.random().nextInt(10,100))
        ),
        3, List.of()
    );

    public static Flux<Order> getUserOrders(Integer userId){
        return Flux.fromIterable(orderTable.get(userId))
            .delayElements(Duration.ofMillis(500))
            .transform(Util.fluxLogger("user " + userId));
    }
}
