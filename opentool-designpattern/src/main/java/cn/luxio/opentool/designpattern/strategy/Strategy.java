package cn.luxio.opentool.designpattern.strategy;

public interface Strategy<K> {

    /**
     * 策略唯一标识
     */
    K getKey();
}
