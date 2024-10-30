package pl.piotrfigura.reactive.s06.task;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import reactor.core.publisher.Flux;

class RevenueService implements OrderProcessor{

    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        var currentRevenue = db.getOrDefault(order.cetegory(), 0);
        var updateRevenue = currentRevenue + order.price();
        db.put(order.cetegory(), updateRevenue);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
            .map(i -> this.db.toString());
    }
}
