package pl.piotrfigura.reactive.s07;

import pl.piotrfigura.reactive.common.Util;
import pl.piotrfigura.reactive.s07.client.ExternalServiceClient;

class EventLoopIssueFix {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for (int i = 0; i < 5; i++) {
            client.getProductName(i)
                .map(EventLoopIssueFix::process)
                .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(2);
    }

    private static String process(String input){
        Util.sleepSeconds(1);
        return input + "-processed";
    }
}
