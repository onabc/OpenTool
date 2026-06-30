package cn.luxio.opentool.designpattern.decorator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * 默认装饰器执行器。
 *
 * @param <T> 被装饰对象类型
 */
@Component
@RequiredArgsConstructor
public class DefaultDecoratorExecutor<T> implements DecoratorExecutor<T> {

    private final List<? extends Decorator<T>> decorators;

    @Override
    public T decorate(T target) {
        T result = target;
        List<? extends Decorator<T>> orderedDecorators = decorators.stream()
                .sorted(Comparator.comparingInt(Decorator::getOrder))
                .toList();
        for (Decorator<T> decorator : orderedDecorators) {
            result = decorator.decorate(result);
        }
        return result;
    }
}
