package cn.luxio.opentool.designpattern.observer;

/**
 * 事件发布器
 *
 * @param <E> 事件类型
 */
public interface EventPublisher<E extends DomainEvent> {

    /**
     * 发布事件
     *
     * @param event 事件对象
     * @return 已触发的监听器数量
     */
    int publish(E event);
}
