package cn.luxio.opentool.designpattern.observer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Configuration
@ComponentScan(basePackageClasses = OrderEventRecorder.class)
@SpringJUnitConfig(ObserverTest.class)
public class ObserverTest {

    @Autowired
    private List<EventListener<OrderEvent>> listeners;

    @Autowired
    private OrderEventRecorder recorder;

    @Test
    public void publishShouldNotifySupportedListeners() {
        recorder.clear();
        DefaultEventPublisher<OrderEvent> publisher = new DefaultEventPublisher<>(listeners);

        int count = publisher.publish(new OrderCreatedEvent("NO1001"));

        assertEquals(2, count);
        assertEquals(List.of("audit:NO1001", "notice:NO1001"), recorder.records());
    }

    @Test
    public void publishShouldSkipUnsupportedListeners() {
        recorder.clear();
        DefaultEventPublisher<OrderEvent> publisher = new DefaultEventPublisher<>(listeners);

        int count = publisher.publish(new OrderCanceledEvent("NO1002"));

        assertEquals(1, count);
        assertEquals(List.of("audit:NO1002"), recorder.records());
    }
}

