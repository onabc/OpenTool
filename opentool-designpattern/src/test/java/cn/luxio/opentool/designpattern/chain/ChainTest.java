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
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChainTest extends ChainExecutor {

    @Test
    public void executeShouldRunOrderedActionsSuccessfully() {
        List<AbstractChainAction> actions = new ArrayList<>(List.of(
                new ThirdAction(),
                new FirstAction(),
                new SecondAction()
        ));

        AnnotationAwareOrderComparator.sort(actions);

        assertEquals(List.of(FirstAction.class, SecondAction.class, ThirdAction.class),
                actions.stream().map(Object::getClass).toList());

        Result<ChainResult> result = execute(new ChainRequest(), new ChainContext(), actions);

        assertTrue(result.getSuccess());
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
    }

    @Test
    public void executeShouldReturnFailedWhenActionsAreEmpty() {
        Result<ChainResult> result = execute(new ChainRequest(), new ChainContext(), List.of());

        assertFalse(result.getSuccess());
        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertEquals(ResultCode.FAILED.getMessage(), result.getMessage());
    }

    @Test
    public void executeShouldStopAtFirstFailedAction() {
        List<String> records = new ArrayList<>();
        Result<ChainResult> failedResult = Result.fail("second failed");
        List<AbstractChainAction> actions = List.of(
                new RecordingAction("first", Result.success(), records),
                new RecordingAction("second", failedResult, records),
                new RecordingAction("third", Result.success(), records)
        );

        Result<ChainResult> result = execute(new ChainRequest(), new ChainContext(), actions);

        assertEquals(failedResult, result);
        assertEquals(List.of("first.process", "second.process", "second.onFailure"), records);
    }

    @Test
    public void executeShouldStopWhenActionReturnsNull() {
        List<String> records = new ArrayList<>();
        List<AbstractChainAction> actions = List.of(
                new RecordingAction("first", Result.success(), records),
                new RecordingAction("second", null, records),
                new RecordingAction("third", Result.success(), records)
        );

        Result<ChainResult> result = execute(new ChainRequest(), new ChainContext(), actions);

        assertNull(result);
        assertEquals(List.of("first.process", "second.process", "second.onFailure"), records);
    }

    private static class RecordingAction extends AbstractChainAction {
        private final String name;
        private final Result<ChainResult> result;
        private final List<String> records;

        private RecordingAction(String name, Result<ChainResult> result, List<String> records) {
            this.name = name;
            this.result = result;
            this.records = records;
        }

        @Override
        public Result<ChainResult> process(ChainRequest request, ChainContext context) {
            records.add(name + ".process");
            return result;
        }

        @Override
        public void onFailure(ChainRequest request, ChainContext context, Result<ChainResult> result) {
            records.add(name + ".onFailure");
        }
    }
}
