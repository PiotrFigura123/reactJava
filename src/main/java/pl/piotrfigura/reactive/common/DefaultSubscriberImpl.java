package pl.piotrfigura.reactive.common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DefaultSubscriberImpl<T> implements Subscriber<T> {

    public static final Logger log = LoggerFactory.getLogger(DefaultSubscriberImpl.class);
    private final String name;

    public DefaultSubscriberImpl(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        log.info("{} received {}",this.name, item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} error ",this.name, throwable);
    }

    @Override
    public void onComplete() {
        log.info("{} completed!", this.name);
    }
}
