package cn.luxio.opentool.designpattern.strategy.handler;

import cn.luxio.opentool.designpattern.strategy.enums.StrategyType;
import org.springframework.stereotype.Component;

@Component
public class UnknownHandler implements StrategyHandler{
    @Override
    public void doHandler() {
        System.out.println("UnknownHandler");
    }

    @Override
    public StrategyType getKey() {
        return StrategyType.UNKNOWN;
    }
}
