package cn.luxio.opentool.designpattern.chain.executor;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.core.response.ResultCode;

import java.util.List;
import java.util.Objects;

public class ChainExecutor {
    protected <T, U, R> Result<R> execute(T request, U context, List<? extends Action<T, U, R>> handlers) {
        Result<R> failedResult = new Result<R>()
                .setSuccess(false)
                .setCode(ResultCode.FAILED.getCode())
                .setMessage(ResultCode.FAILED.getMessage());

        if (handlers == null || handlers.isEmpty()) {
            return failedResult;
        }

        Result<R> lastResult = null;
        for (Action<T, U, R> handler : handlers) {
            Result<R> result = handler.process(request, context);
            if (Objects.isNull(result)) {
                return failedResult;
            }
            if (!Boolean.TRUE.equals(result.getSuccess())) {
                handler.onFailure(request, context, result);
                return result;
            }
            lastResult = result;
        }
        return lastResult;
    }
}
