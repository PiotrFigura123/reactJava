package pl.piotrfigura.reactive.s02;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s02.client.ExternalServiceClient;

public class NonBlockingIO {

    public static final Logger log = LoggerFactory.getLogger(NonBlockingIO.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        log.info("starting");
        for (int i=1; i <= 5 ; i++){
        client.getProductName(i)
            .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(2);
    }

}
