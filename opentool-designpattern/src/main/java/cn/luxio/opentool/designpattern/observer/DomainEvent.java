package cn.luxio.opentool.designpattern.observer;

/**
 * 领域事件。
 */
public interface DomainEvent {

    /**
     * 事件名称。
     *
     * @return 事件名称
     */
    default String getName() {
        return getClass().getSimpleName();
    }
}
