package cn.luxio.opentool.designpattern.chain.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 责任链节点组件注解。
 * <p>
 * 组合 {@link Component} 与 {@link Order}，用于将责任链节点注册为 Spring Bean，
 * 并声明节点在责任链中的执行顺序。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Order
public @interface OrderedComponent {

    /**
     * 节点执行顺序，数值越小优先级越高。
     */
    @AliasFor(
            annotation = Order.class
    )
    int value() default Integer.MAX_VALUE;

    /**
     * Spring Bean 名称，等价于 {@link Component#value()}。
     */
    @AliasFor(
            annotation = Component.class,
            attribute = "value"
    )
    String name() default "";
}
