package cn.luxio.opentool.designpattern.strategy.handler;

import cn.luxio.opentool.designpattern.strategy.DefaultStrategyFactory;
import cn.luxio.opentool.designpattern.strategy.enums.StrategyType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StrategyFactoryImpl extends DefaultStrategyFactory<StrategyType, StrategyHandler> {
    public StrategyFactoryImpl(List<StrategyHandler> strategies) {
        super(strategies);
    }
}
