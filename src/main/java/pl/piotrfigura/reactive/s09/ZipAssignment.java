package pl.piotrfigura.reactive.s09;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.Flux;

class ZipAssignment {

    public static final Logger log = LoggerFactory.getLogger(ZipAssignment.class);

    record Car(String t1, String t2, String t3){};
    public static void main(String[] args) {


        Util.sleepSeconds(5);
    }

}
