package pl.piotrfigura.reactive.s09.app;

import java.util.Map;
import reactor.core.publisher.Mono;

public class PaymentService {

    public static final Map<Integer, Integer> userBalance = Map.of(
        1,100,
        2,200,
        3,300
    );

    public static Mono<Integer> getUserBalance(Integer userId){
        return Mono.fromSupplier(() -> userBalance.get(userId));
    }
}
