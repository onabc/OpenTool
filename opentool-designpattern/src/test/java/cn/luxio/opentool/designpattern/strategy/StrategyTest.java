package cn.luxio.opentool.designpattern.strategy;

import cn.luxio.opentool.designpattern.strategy.enums.StrategyType;
import cn.luxio.opentool.designpattern.strategy.handler.FirstHandler;
import cn.luxio.opentool.designpattern.strategy.handler.SecondHandler;
import cn.luxio.opentool.designpattern.strategy.handler.StrategyFactoryImpl;
import cn.luxio.opentool.designpattern.strategy.handler.StrategyHandler;
import cn.luxio.opentool.designpattern.strategy.handler.UnknownHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Configuration
@ComponentScan(basePackageClasses = StrategyHandler.class)
@SpringJUnitConfig(StrategyTest.class)
public class StrategyTest {

    @Autowired
    private StrategyFactoryImpl strategyFactory;

    @Autowired
    private List<StrategyHandler> strategyHandlers;

    @Test
    public void getShouldReturnFirstHandler() {
        StrategyHandler strategyHandler = strategyFactory.get(StrategyType.FIRST);

        assertInstanceOf(FirstHandler.class, strategyHandler);
        assertDoesNotThrow(strategyHandler::doHandler);
    }

    @Test
    public void getShouldReturnSecondHandler() {
        StrategyHandler strategyHandler = strategyFactory.get(StrategyType.SECOND);

        assertInstanceOf(SecondHandler.class, strategyHandler);
        assertDoesNotThrow(strategyHandler::doHandler);
    }

    @Test
    public void getShouldReturnUnknownHandler() {
        StrategyHandler strategyHandler = strategyFactory.get(StrategyType.UNKNOWN);

        assertInstanceOf(UnknownHandler.class, strategyHandler);
        assertDoesNotThrow(strategyHandler::doHandler);
    }

    @Test
    public void containsShouldReturnTrueWhenStrategyExists() {
        assertTrue(strategyFactory.contains(StrategyType.FIRST));
        assertTrue(strategyFactory.contains(StrategyType.SECOND));
        assertTrue(strategyFactory.contains(StrategyType.UNKNOWN));
    }

    @Test
    public void componentScanShouldInjectAllStrategyHandlers() {
        assertEquals(3, strategyHandlers.size());
        assertTrue(strategyHandlers.stream().anyMatch(FirstHandler.class::isInstance));
        assertTrue(strategyHandlers.stream().anyMatch(SecondHandler.class::isInstance));
        assertTrue(strategyHandlers.stream().anyMatch(UnknownHandler.class::isInstance));
    }

    @Test
    public void constructorShouldRejectDuplicateStrategyKey() {
        assertThrows(IllegalArgumentException.class, () ->
                new StrategyFactoryImpl(List.of(new FirstHandler(), new FirstHandler()))
        );
    }

    @Test
    public void constructorShouldRejectNullStrategies() {
        assertThrows(NullPointerException.class, () -> new StrategyFactoryImpl(null));
    }

    @Test
    public void constructorShouldRejectNullStrategy() {
        assertThrows(NullPointerException.class, () ->
                new StrategyFactoryImpl(java.util.Collections.singletonList(null))
        );
    }

}
