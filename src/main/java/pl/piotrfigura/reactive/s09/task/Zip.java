package pl.piotrfigura.reactive.s09.task;

import pl.piotrfigura.reactive.common.Util;

class Zip {

    public static void main(String[] args) {

        var clinet = new ExternalServiceClient();

        for (int i = 0; i < 10; i++) {
            clinet.getProduct(i)
                .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(3);
    }
}
