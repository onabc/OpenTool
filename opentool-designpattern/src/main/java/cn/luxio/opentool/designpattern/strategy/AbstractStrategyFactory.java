package cn.luxio.opentool.designpattern.strategy;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AbstractStrategyFactory<K, S extends Strategy<K>>
        implements StrategyFactory<K, S> {

    private final Map<K, S> strategyMap;

    public AbstractStrategyFactory(List<S> strategies) {
        this.strategyMap = createMap(strategies);
    }

    private Map<K, S> createMap(List<S> strategies) {
        return Collections.unmodifiableMap(
                strategies.stream()
                        .collect(Collectors.toMap(
                                S::getKey,
                                Function.identity(),
                                (existing, replacement) -> existing
                        ))
        );
    }

    @Override
    public S get(K key) {
        S strategy = strategyMap.get(key);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for key: " + key);
        }
        return strategy;
    }

    @Override
    public boolean contains(K key) {
        return strategyMap.containsKey(key);
    }
}
