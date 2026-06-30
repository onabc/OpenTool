package cn.luxio.opentool.designpattern.strategy;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 默认策略工厂实现
 * <p>
 * 构造时会将策略列表按策略标识建立索引，并在发现空策略、空策略标识或重复策略标识时快速失败
 *
 * @param <K> 策略标识类型
 * @param <S> 策略实现类型
 */
public class DefaultStrategyFactory<K, S extends Strategy<K>>
        implements StrategyFactory<K, S> {

    private final Map<K, S> strategyMap;

    /**
     * 创建默认策略工厂
     *
     * @param strategies 策略列表
     */
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
    public Optional<S> find(K key) {
        return Optional.ofNullable(strategyMap.get(key));
    }

    @Override
    public S get(K key) {
        return find(key)
                .orElseThrow(() -> new IllegalArgumentException("No strategy found for key: " + key));
    }

    @Override
    public boolean contains(K key) {
        return strategyMap.containsKey(key);
    }
}
