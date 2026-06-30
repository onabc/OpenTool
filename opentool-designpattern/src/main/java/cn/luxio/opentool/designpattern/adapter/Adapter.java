package cn.luxio.opentool.designpattern.adapter;

/**
 * 适配器。
 *
 * @param <S> 源对象类型
 * @param <T> 目标对象类型
 */
public interface Adapter<S, T> {

    /**
     * 判断当前适配器是否支持该源对象。
     *
     * @param source 源对象
     * @return 支持返回 {@code true}，否则返回 {@code false}
     */
    boolean supports(S source);

    /**
     * 将源对象适配为目标对象。
     *
     * @param source 源对象
     * @return 目标对象
     */
    T adapt(S source);
}
