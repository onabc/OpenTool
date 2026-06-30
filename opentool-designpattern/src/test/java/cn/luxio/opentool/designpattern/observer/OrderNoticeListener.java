package cn.luxio.opentool.designpattern.observer;

import org.springframework.stereotype.Component;

@Component
public class OrderNoticeListener implements EventListener<OrderEvent> {

    private final OrderEventRecorder recorder;

    OrderNoticeListener(OrderEventRecorder recorder) {
        this.recorder = recorder;
    }

    @Override
    public boolean supports(OrderEvent event) {
        return event instanceof OrderCreatedEvent;
    }

    @Override
    public void onEvent(OrderEvent event) {
        recorder.add("notice:" + event.orderNo());
    }
}
