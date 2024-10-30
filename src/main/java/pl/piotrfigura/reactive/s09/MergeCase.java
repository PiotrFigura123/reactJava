package pl.piotrfigura.reactive.s09;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s09.helper.Kayak;
import pl.piotrfigura.reactive.s09.helper.NameGenerator;
import reactor.core.publisher.Flux;

class MergeCase {


    public static final Logger log = LoggerFactory.getLogger(NameGenerator.class);


    public static void main(String[] args) {
        Kayak.getFlight()
                .subscribe(Util.subscriber());
        Util.sleepSeconds(3);
    }


}
