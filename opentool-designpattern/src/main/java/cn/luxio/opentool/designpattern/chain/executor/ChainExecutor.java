package cn.luxio.opentool.designpattern.chain.executor;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.core.response.ResultCode;

import java.util.List;

public class ChainExecutor {
    protected <T, U, R> Result<R> execute(T request, U context, List<? extends Action<T, U, R>> handlers) {
        if (handlers == null || handlers.isEmpty()) {
            return new Result<R>()
                    .setSuccess(false)
                    .setCode(ResultCode.FAILED.getCode())
                    .setMessage(ResultCode.FAILED.getMessage());
        }

        for (Action<T, U, R> handler : handlers) {
            Result<R> result = handler.process(request, context);
            if (result == null || !result.getSuccess()) {
                handler.onFailure(request, context, result);
                return result;
            }
        }
        return Result.success();
    }
}
