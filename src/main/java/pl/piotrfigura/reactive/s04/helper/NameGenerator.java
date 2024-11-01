package pl.piotrfigura.reactive.s04.helper;

import java.util.function.Consumer;
import pl.piotrfigura.reactive.common.Util;
import reactor.core.publisher.FluxSink;

public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        System.out.println("got a flux sink");
        this.sink = stringFluxSink;
    }

    public void generate(){
        this.sink.next((Util.faker().name().firstName()));
    }
}
