package cn.luxio.opentool.designpattern.strategy;

import cn.luxio.opentool.designpattern.strategy.enums.StrategyType;
import cn.luxio.opentool.designpattern.strategy.handler.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrategyTest {

    @Test
    public void getShouldReturnFirstHandler() {
        FirstHandler firstHandler = new FirstHandler();
        StrategyFactoryImpl strategyFactory =
                createStrategyFactory(firstHandler, new SecondHandler(), new UnknownHandler());

        StrategyHandler strategyHandler = strategyFactory.get(StrategyType.FIRST);

        assertSame(firstHandler, strategyHandler);
        assertInstanceOf(FirstHandler.class, strategyHandler);
        assertDoesNotThrow(strategyHandler::doHandler);
    }

    @Test
    public void getShouldReturnSecondHandler() {
        SecondHandler secondHandler = new SecondHandler();
        StrategyFactoryImpl strategyFactory =
                createStrategyFactory(new FirstHandler(), secondHandler, new UnknownHandler());

        StrategyHandler strategyHandler = strategyFactory.get(StrategyType.SECOND);

        assertSame(secondHandler, strategyHandler);
        assertInstanceOf(SecondHandler.class, strategyHandler);
        assertDoesNotThrow(strategyHandler::doHandler);
    }

    @Test
    public void containsShouldReturnTrueWhenStrategyExists() {
        StrategyFactoryImpl strategyFactory =
                createStrategyFactory(new FirstHandler(), new SecondHandler());

        assertTrue(strategyFactory.contains(StrategyType.FIRST));
        assertTrue(strategyFactory.contains(StrategyType.SECOND));
    }

    @Test
    public void containsShouldReturnFalseWhenStrategyDoesNotExist() {
        StrategyFactoryImpl strategyFactory =
                createStrategyFactory(new FirstHandler(), new SecondHandler());

        assertFalse(strategyFactory.contains(StrategyType.UNKNOWN));
    }

    @Test
    public void getShouldThrowWhenStrategyDoesNotExist() {
        StrategyFactoryImpl strategyFactory =
                createStrategyFactory(new FirstHandler(), new SecondHandler());

        assertThrows(IllegalArgumentException.class, () -> strategyFactory.get(StrategyType.UNKNOWN));
    }

    private StrategyFactoryImpl createStrategyFactory(
            StrategyHandler... strategyHandlers
    ) {
        return new StrategyFactoryImpl(
                List.of(strategyHandlers)
        );
    }

}
