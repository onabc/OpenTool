package cn.luxio.opentool.designpattern.chain;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.core.response.ResultCode;
import cn.luxio.opentool.designpattern.chain.executor.ChainAction;
import cn.luxio.opentool.designpattern.chain.executor.ChainExecutor;
import cn.luxio.opentool.designpattern.chain.action.FirstAction;
import cn.luxio.opentool.designpattern.chain.action.SecondAction;
import cn.luxio.opentool.designpattern.chain.action.ThirdAction;
import cn.luxio.opentool.designpattern.chain.model.ChainContext;
import cn.luxio.opentool.designpattern.chain.model.ChainRequest;
import cn.luxio.opentool.designpattern.chain.model.ChainResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Configuration
@ComponentScan(basePackageClasses = AbstractChainAction.class)
@SpringJUnitConfig(ChainTest.class)
public class ChainTest {

    @Autowired
    private ChainExecutor<ChainRequest, ChainContext, ChainResult> chainExecutor;

    @Autowired
    private List<AbstractChainAction> actions;

    @Test
    public void executeShouldRunOrderedActionsSuccessfully() {
        assertEquals(List.of(FirstAction.class, SecondAction.class, ThirdAction.class),
                actions.stream().map(Object::getClass).toList());

        Result<ChainResult> result = chainExecutor.execute(new ChainRequest(), new ChainContext());

        assertTrue(result.getSuccess());
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertNotNull(result.getData());
    }

    @Test
    public void componentScanShouldInjectOrderedActions() {
        assertEquals(3, actions.size());
        assertEquals(List.of(FirstAction.class, SecondAction.class, ThirdAction.class),
                actions.stream().map(Object::getClass).toList());
    }

    @Test
    public void executeShouldCallFailureHookWhenActionReturnsNull() {
        List<String> records = new ArrayList<>();
        ChainAction<ChainRequest, ChainContext, ChainResult> action = new ChainAction<>() {
            @Override
            public Result<ChainResult> process(ChainRequest request, ChainContext context) {
                records.add("process");
                return null;
            }

            @Override
            public void onFailure(ChainRequest request, ChainContext context, Result<ChainResult> result) {
                records.add("onFailure");
                assertNull(result);
            }
        };
        Result<ChainResult> result = new ChainExecutor<>(List.of(action))
                .execute(new ChainRequest(), new ChainContext());

        assertFalse(result.getSuccess());
        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertEquals(List.of("process", "onFailure"), records);
    }

    @Test
    public void executeShouldRollbackSuccessfulActionsWhenLaterActionFails() {
        List<String> records = new ArrayList<>();
        Result<ChainResult> failedResult = Result.fail("third failed");
        ChainAction<ChainRequest, ChainContext, ChainResult> firstAction = new ChainAction<>() {
            @Override
            public Result<ChainResult> process(ChainRequest request, ChainContext context) {
                records.add("first.process");
                return Result.success(new ChainResult());
            }

            @Override
            public void onRollback(ChainRequest request, ChainContext context, Result<ChainResult> result) {
                records.add("first.rollback");
                assertSame(failedResult, result);
            }
        };
        ChainAction<ChainRequest, ChainContext, ChainResult> secondAction = new ChainAction<>() {
            @Override
            public Result<ChainResult> process(ChainRequest request, ChainContext context) {
                records.add("second.process");
                return Result.success(new ChainResult());
            }

            @Override
            public void onRollback(ChainRequest request, ChainContext context, Result<ChainResult> result) {
                records.add("second.rollback");
                assertSame(failedResult, result);
            }
        };
        ChainAction<ChainRequest, ChainContext, ChainResult> thirdAction = new ChainAction<>() {
            @Override
            public Result<ChainResult> process(ChainRequest request, ChainContext context) {
                records.add("third.process");
                return failedResult;
            }

            @Override
            public void onFailure(ChainRequest request, ChainContext context, Result<ChainResult> result) {
                records.add("third.onFailure");
                assertSame(failedResult, result);
            }
        };

        Result<ChainResult> result = new ChainExecutor<>(List.of(firstAction, secondAction, thirdAction))
                .execute(new ChainRequest(), new ChainContext());

        assertSame(failedResult, result);
        assertEquals(List.of("first.process", "second.process", "third.process",
                "third.onFailure", "second.rollback", "first.rollback"), records);
    }

}
