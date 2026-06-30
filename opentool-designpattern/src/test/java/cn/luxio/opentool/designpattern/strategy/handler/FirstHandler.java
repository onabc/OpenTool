package cn.luxio.opentool.designpattern.strategy.handler;

import cn.luxio.opentool.designpattern.strategy.enums.StrategyType;
import org.springframework.stereotype.Component;

@Component
public class FirstHandler implements StrategyHandler{
    @Override
    public void doHandler() {
        System.out.println("FirstHandler");
    }

    @Override
    public StrategyType getKey() {
        return StrategyType.FIRST;
    }
}
