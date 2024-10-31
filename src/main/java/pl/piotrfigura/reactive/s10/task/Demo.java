package pl.piotrfigura.reactive.s10.task;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.util.retry.Retry;

class Demo {
    public static final Logger log = LoggerFactory.getLogger(Demo.class);
    public static void main(String[] args) {

//        repeat();
retry();
        Util.sleepSeconds(60);

    }
    private static void repeat(){
        var client = new ExternalServiceClient();
        client.getCountry()
            .repeat()
            .takeUntil(c -> c.equalsIgnoreCase("canada"))
            .subscribe(Util.subscriber());
    }

    private static void retry(){
        var client = new ExternalServiceClient();
        client.getProductName(2)
            .retryWhen(retryOnServerError())
            .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError(){
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
            .filter(ex -> ServerError.class.equals(ex.getClass()))
            .doBeforeRetry(rs -> log.info("retrying {}", rs.failure().getMessage()));
    }

}
