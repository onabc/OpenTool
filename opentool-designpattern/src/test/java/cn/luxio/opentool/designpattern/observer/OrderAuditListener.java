package cn.luxio.opentool.designpattern.observer;

import org.springframework.stereotype.Component;

@Component
public class OrderAuditListener implements EventListener<OrderEvent> {

    private final OrderEventRecorder recorder;

    OrderAuditListener(OrderEventRecorder recorder) {
        this.recorder = recorder;
    }

    @Override
    public boolean supports(OrderEvent event) {
        return true;
    }

    @Override
    public void onEvent(OrderEvent event) {
        recorder.add("audit:" + event.orderNo());
    }
}
