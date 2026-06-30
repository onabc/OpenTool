package cn.luxio.opentool.designpattern.strategy.handler;

import cn.luxio.opentool.designpattern.strategy.enums.StrategyType;
import org.springframework.stereotype.Component;

@Component
public class SecondHandler implements StrategyHandler{
    @Override
    public void doHandler() {
        System.out.println("SecondHandler");
    }

    @Override
    public StrategyType getKey() {
        return StrategyType.SECOND;
    }
}
