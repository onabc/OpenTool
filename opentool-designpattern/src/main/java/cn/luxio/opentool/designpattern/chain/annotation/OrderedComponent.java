package cn.luxio.opentool.designpattern.chain.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Combines {@link Component} and {@link Order} for chain actions.
 * <p>
 * Use {@link #value()} to define the action order, and use {@link #name()} to
 * define the Spring bean name when a custom bean name is required.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Order
public @interface OrderedComponent {

    /**
     * Action order in the chain. Lower values have higher priority.
     */
    @AliasFor(
            annotation = Order.class
    )
    int value() default Integer.MAX_VALUE;

    /**
     * Spring bean name, equivalent to {@link Component#value()}.
     */
    @AliasFor(
            annotation = Component.class,
            attribute = "value"
    )
    String name() default "";
}
