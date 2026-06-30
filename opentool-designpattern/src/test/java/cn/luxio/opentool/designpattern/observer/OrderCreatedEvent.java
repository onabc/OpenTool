package cn.luxio.opentool.designpattern.observer;

public record OrderCreatedEvent(String orderNo) implements OrderEvent {
}
