package pl.piotrfigura.reactive.s09;

import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s09.app.Order;
import java.util.List;
import pl.piotrfigura.reactive.s09.app.OrderService;
import pl.piotrfigura.reactive.s09.app.PaymentService;
import pl.piotrfigura.reactive.s09.app.User;
import pl.piotrfigura.reactive.s09.app.UserService;
import reactor.core.publisher.Mono;

class Task {
    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders){}

    public static void main(String[] args) {

        UserService.getAllUsers()
            .flatMap(Task::getUserInformation)
            .subscribe(Util.subscriber());
        Util.sleepSeconds(4);
    }

    private static Mono<UserInformation> getUserInformation(User user){
        return Mono.zip(
            PaymentService.getUserBalance(user.id()),
            OrderService.getUserOrders(user.id()).collectList()
        )
            .map(t -> new UserInformation(user.id(), user.username(), t.getT1(), t.getT2()));
    }
}
