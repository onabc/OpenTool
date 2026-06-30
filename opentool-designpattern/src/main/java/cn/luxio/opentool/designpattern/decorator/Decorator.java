package cn.luxio.opentool.designpattern.decorator;

/**
 * 装饰器。
 *
 * @param <T> 被装饰对象类型
 */
public interface Decorator<T> {

    /**
     * 装饰器执行顺序，数值越小优先级越高。
     *
     * @return 执行顺序
     */
    default int getOrder() {
        return Integer.MAX_VALUE;
    }

    /**
     * 执行装饰逻辑。
     *
     * @param target 被装饰对象
     * @return 装饰后的对象
     */
    T decorate(T target);
}
