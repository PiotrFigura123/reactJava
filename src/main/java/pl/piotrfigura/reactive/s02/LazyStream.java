package pl.piotrfigura.reactive.s02;


import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LazyStream {

    public static final Logger logger = LoggerFactory.getLogger(LazyStream.class);

    public static void main(String[] args) {
        Stream.of(1)
            .peek(i -> logger.info("received : {}", i))
            .toList();

    }
}
