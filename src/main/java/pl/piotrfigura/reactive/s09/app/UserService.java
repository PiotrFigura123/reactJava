package pl.piotrfigura.reactive.s09.app;

import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserService {

    public static final Map<String, Integer> userTable = Map.of(
        "sam", 1,
        "mike", 2,
        "jake", 3
    );

    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(userTable.entrySet())
            .map(entry -> new User(entry.getValue(), entry.getKey()));
    }

    public static Mono<Integer> getUserId(String username) {
        return Mono.fromSupplier(() -> userTable.get(username));
    }
}
