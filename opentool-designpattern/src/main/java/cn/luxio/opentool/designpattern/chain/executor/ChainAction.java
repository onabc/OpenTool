package cn.luxio.opentool.designpattern.chain.executor;

import cn.luxio.opentool.core.response.Result;

/**
 * 责任链节点。
 *
 * @param <T> 请求对象类型
 * @param <U> 上下文对象类型
 * @param <R> 执行结果数据类型
 */
public interface ChainAction<T, U, R> {
    /**
     * 执行当前节点的业务逻辑。
     *
     * @param request 请求对象
     * @param context 执行上下文
     * @return 当前节点执行结果
     */
    Result<R> process(T request, U context);

    /**
     * 当前节点执行失败时的回调。
     *
     * @param request 请求对象
     * @param context 执行上下文
     * @param result 当前节点返回的失败结果；当 {@link #process(Object, Object)}
     *               返回 {@code null} 时该值为 {@code null}
     */
    default void onFailure(T request, U context, Result<R> result) {
    }

    /**
     * 后续节点执行失败时的回滚回调。
     * <p>
     * 仅已执行成功的节点会触发该回调，触发顺序与执行顺序相反。
     *
     * @param request 请求对象
     * @param context 执行上下文
     * @param result 导致责任链中断的失败结果；当失败节点返回 {@code null} 时该值为 {@code null}
     */
    default void onRollback(T request, U context, Result<R> result) {
    }
}
