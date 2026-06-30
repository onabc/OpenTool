package cn.luxio.opentool.designpattern.observer;

/**
 * 事件监听器。
 *
 * @param <E> 事件类型
 */
public interface EventListener<E extends DomainEvent> {

    /**
     * 判断当前监听器是否支持该事件。
     *
     * @param event 事件对象
     * @return 支持返回 {@code true}，否则返回 {@code false}
     */
    boolean supports(E event);

    /**
     * 处理事件。
     *
     * @param event 事件对象
     */
    void onEvent(E event);
}
