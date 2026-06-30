package cn.luxio.opentool.designpattern.observer;

public interface OrderEvent extends DomainEvent {

    String orderNo();
}
