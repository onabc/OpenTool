package cn.luxio.opentool.designpattern.decorator;

/**
 * 装饰器执行器。
 *
 * @param <T> 被装饰对象类型
 */
public interface DecoratorExecutor<T> {

    /**
     * 按顺序执行装饰器。
     *
     * @param target 被装饰对象
     * @return 装饰后的对象
     */
    T decorate(T target);
}
