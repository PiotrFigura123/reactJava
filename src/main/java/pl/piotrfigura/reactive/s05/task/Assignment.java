package pl.piotrfigura.reactive.s05.task;

import pl.piotrfigura.reactive.common.Util;

class Assignment {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 0; i < 5; i++) {
            client.getProductName(i)
                .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(3);
    }

}
