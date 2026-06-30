package cn.luxio.opentool.designpattern.strategy;

import java.util.Optional;

/**
 * 策略工厂。
 *
 * @param <K> 策略标识类型
 * @param <S> 策略实现类型
 */
public interface StrategyFactory<K, S extends Strategy<K>> {

    /**
     * 根据策略标识查找策略。
     *
     * @param key 策略标识
     * @return 匹配的策略；不存在时返回空
     */
    Optional<S> find(K key);

    /**
     * 根据策略标识获取策略。
     *
     * @param key 策略标识
     * @return 匹配的策略
     * @throws IllegalArgumentException 当策略不存在时抛出
     */
    S get(K key);

    /**
     * 判断指定策略是否存在。
     *
     * @param key 策略标识
     * @return 存在返回 {@code true}，否则返回 {@code false}
     */
    boolean contains(K key);
}
