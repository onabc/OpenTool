package cn.luxio.opentool.designpattern.strategy.handler;

import cn.luxio.opentool.designpattern.strategy.Strategy;
import cn.luxio.opentool.designpattern.strategy.enums.StrategyType;

public interface StrategyHandler extends Strategy<StrategyType> {

    void doHandler();
}
