package cn.luxio.opentool.designpattern.adapter;

/**
 * 适配器执行器
 *
 * @param <S> 源对象类型
 * @param <T> 目标对象类型
 */
public interface AdapterExecutor<S, T> {

    /**
     * 执行适配
     *
     * @param source 源对象
     * @return 目标对象
     */
    T adapt(S source);
}
