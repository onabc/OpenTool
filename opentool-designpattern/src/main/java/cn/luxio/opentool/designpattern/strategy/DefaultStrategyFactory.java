package cn.luxio.opentool.designpattern.strategy;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DefaultStrategyFactory<K, S extends Strategy<K>>
        implements StrategyFactory<K, S> {

    private final Map<K, S> strategyMap;

    public DefaultStrategyFactory(List<S> strategies) {
        this.strategyMap = createMap(strategies);
    }

    private Map<K, S> createMap(List<S> strategies) {
        Objects.requireNonNull(strategies, "strategies must not be null");

        Map<K, S> strategyMap = new LinkedHashMap<>();
        for (S strategy : strategies) {
            Objects.requireNonNull(strategy, "strategy must not be null");
            K key = Objects.requireNonNull(strategy.getKey(), "strategy key must not be null");
            S existing = strategyMap.putIfAbsent(key, strategy);
            if (existing != null) {
                throw new IllegalArgumentException("Duplicate strategy key: " + key);
            }
        }
        return Collections.unmodifiableMap(strategyMap);
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
