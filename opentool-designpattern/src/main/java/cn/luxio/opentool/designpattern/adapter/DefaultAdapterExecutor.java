package cn.luxio.opentool.designpattern.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 默认适配器执行器
 *
 * @param <S> 源对象类型
 * @param <T> 目标对象类型
 */
@Component
@RequiredArgsConstructor
public class DefaultAdapterExecutor<S, T> implements AdapterExecutor<S, T> {

    private final List<? extends Adapter<S, T>> adapters;

    @Override
    public T adapt(S source) {
        Objects.requireNonNull(source, "source must not be null");
        for (Adapter<S, T> adapter : adapters) {
            if (adapter.supports(source)) {
                return adapter.adapt(source);
            }
        }
        throw new IllegalArgumentException("No adapter found for source: " + source);
    }
}
