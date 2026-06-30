package cn.luxio.opentool.designpattern.chain.executor;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.core.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 责任链执行器
 *
 * @param <T> 请求对象类型
 * @param <U> 上下文对象类型
 * @param <R> 执行结果数据类型
 */
@Component
@RequiredArgsConstructor
public class ChainExecutor<T, U, R> {
    private final List<? extends ChainAction<T, U, R>> actions;

    /**
     * 按顺序执行已配置的责任链节点
     * <p>
     * 当责任链为空时返回失败结果；当某个节点返回 {@code null} 或非成功结果时，
     * 触发该节点的失败回调，并按逆序触发已成功节点的回滚回调后短路返回；
     * 当所有节点均执行成功时，返回最后一个成功节点的结果
     *
     * @param request 请求对象
     * @param context 执行上下文
     * @return 责任链执行结果
     */
    public Result<R> execute(T request, U context) {
        Result<R> failedResult = new Result<R>()
                .setSuccess(false)
                .setCode(ResultCode.FAILED.getCode())
                .setMessage(ResultCode.FAILED.getMessage());

        if (actions == null || actions.isEmpty()) {
            return failedResult;
        }

        Result<R> lastResult = null;
        List<ChainAction<T, U, R>> executedActions = new ArrayList<>();
        for (ChainAction<T, U, R> handler : actions) {
            Result<R> result = handler.process(request, context);
            if (Objects.isNull(result)) {
                handler.onFailure(request, context, null);
                rollback(request, context, null, executedActions);
                return failedResult;
            }
            if (!Boolean.TRUE.equals(result.getSuccess())) {
                handler.onFailure(request, context, result);
                rollback(request, context, result, executedActions);
                return result;
            }
            executedActions.add(handler);
            lastResult = result;
        }
        return lastResult;
    }

    /**
     * 按执行顺序的反方向回滚已成功节点
     *
     * @param request 请求对象
     * @param context 执行上下文
     * @param result 导致责任链中断的失败结果
     * @param executedActions 已执行成功的节点
     */
    private void rollback(T request, U context, Result<R> result, List<ChainAction<T, U, R>> executedActions) {
        for (int i = executedActions.size() - 1; i >= 0; i--) {
            executedActions.get(i).onRollback(request, context, result);
        }
    }
}
