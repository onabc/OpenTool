package cn.luxio.opentool.designpattern.chain.executor;


import cn.luxio.opentool.core.response.Result;

public interface Action<T, U, R> {
    /**
     * 业务处理逻辑
     */
    Result<R> process(T request, U context);

    default void onFailure(T request, U context, Result<R> result) {
    }
}
