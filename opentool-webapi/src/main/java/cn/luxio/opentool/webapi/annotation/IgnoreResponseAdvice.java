package cn.luxio.opentool.webapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略统一响应包装
 * <p>
 * 标注在控制器类或方法上时，{@code GracefulResponseAdvice} 不会对返回值进行 {@code Result} 包装
 * </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}
