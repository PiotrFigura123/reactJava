package pl.piotrfigura.reactive.s02;

import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Mono;

class MonoEmptyError {

    public static void main(String[] args) {
        getUsername(3)
            .subscribe(
                s-> System.out.println(s),
                err -> {}
            );
        getUsername(1)
            .subscribe(
                Util.subscriber("sub1")
            );
    }

    private static Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();   //similar to null
            default -> Mono.error(new RuntimeException("invalid id"));
        };
    }

}
