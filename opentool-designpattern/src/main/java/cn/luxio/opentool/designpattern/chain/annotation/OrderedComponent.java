package cn.luxio.opentool.designpattern.chain.annotation;



import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Order
public @interface OrderedComponent {

    @AliasFor(
            annotation = Order.class
    )
    int value() default Integer.MAX_VALUE;

    @AliasFor(
            annotation = Component.class,
            attribute = "value"
    )
    String name() default "";
}
