package pl.piotrfigura.reactive.s06.task;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import reactor.core.publisher.Flux;

class InventoryService implements OrderProcessor{

    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        var currentInventory = db.getOrDefault(order.cetegory(), 500);
        var updateInventory = currentInventory -  order.quantity();
        db.put(order.cetegory(), updateInventory);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
            .map(i -> this.db.toString());
    }
}
