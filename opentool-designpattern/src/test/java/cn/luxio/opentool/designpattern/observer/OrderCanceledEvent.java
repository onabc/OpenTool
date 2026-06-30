package cn.luxio.opentool.designpattern.observer;

public record OrderCanceledEvent(String orderNo) implements OrderEvent {
}
