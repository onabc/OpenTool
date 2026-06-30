package cn.luxio.opentool.designpattern.observer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 默认事件发布器
 *
 * @param <E> 事件类型
 */
@Component
@RequiredArgsConstructor
public class DefaultEventPublisher<E extends DomainEvent> implements EventPublisher<E> {

    private final List<? extends EventListener<E>> listeners;

    @Override
    public int publish(E event) {
        Objects.requireNonNull(event, "event must not be null");
        int count = 0;
        for (EventListener<E> listener : listeners) {
            if (listener.supports(event)) {
                listener.onEvent(event);
                count++;
            }
        }
        return count;
    }
}
