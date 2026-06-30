package cn.luxio.opentool.designpattern.chain;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.core.response.ResultCode;
import cn.luxio.opentool.designpattern.chain.action.AbstractChainAction;
import cn.luxio.opentool.designpattern.chain.action.FirstAction;
import cn.luxio.opentool.designpattern.chain.action.SecondAction;
import cn.luxio.opentool.designpattern.chain.action.ThirdAction;
import cn.luxio.opentool.designpattern.chain.executor.ChainExecutor;
import cn.luxio.opentool.designpattern.chain.model.ChainContext;
import cn.luxio.opentool.designpattern.chain.model.ChainRequest;
import cn.luxio.opentool.designpattern.chain.model.ChainResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Configuration
@ComponentScan(basePackageClasses = AbstractChainAction.class)
@SpringJUnitConfig(ChainTest.class)
public class ChainTest extends ChainExecutor {

    @Autowired
    private List<AbstractChainAction> actions;

    @Test
    public void executeShouldRunOrderedActionsSuccessfully() {
        assertEquals(List.of(FirstAction.class, SecondAction.class, ThirdAction.class),
                actions.stream().map(Object::getClass).toList());

        Result<ChainResult> result = execute(new ChainRequest(), new ChainContext(), actions);

        assertTrue(result.getSuccess());
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertNotNull(result.getData());
    }

    @Test
    public void executeShouldReturnFailedWhenActionsAreEmpty() {
        Result<ChainResult> result = execute(new ChainRequest(), new ChainContext(), List.of());

        assertFalse(result.getSuccess());
        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertEquals(ResultCode.FAILED.getMessage(), result.getMessage());
    }

    @Test
    public void componentScanShouldInjectOrderedActions() {
        assertEquals(3, actions.size());
        assertEquals(List.of(FirstAction.class, SecondAction.class, ThirdAction.class),
                actions.stream().map(Object::getClass).toList());
    }

}
