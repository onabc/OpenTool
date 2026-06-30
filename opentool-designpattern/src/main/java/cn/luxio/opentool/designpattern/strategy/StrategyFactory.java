package cn.luxio.opentool.designpattern.strategy;

public interface StrategyFactory<K, S extends Strategy<K>> {

    S get(K key);

    boolean contains(K key);
}

