package cn.luxio.opentool.webapi.handler;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.webapi.annotation.IgnoreResponseAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * 统一响应包装处理器
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class GracefulResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    /**
     * 判断当前返回值是否需要包装
     *
     * @param returnType 控制器方法返回值信息
     * @param converterType 消息转换器类型
     * @return 需要包装返回 {@code true}，否则返回 {@code false}
     */
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        Method method = returnType.getMethod();
        return method == null || !method.isAnnotationPresent(IgnoreResponseAdvice.class);
    }

    /**
     * 将普通返回值包装成统一响应结构
     *
     * @param body 原始返回值
     * @param returnType 控制器方法返回值信息
     * @param selectedContentType 响应媒体类型
     * @param selectedConverterType 消息转换器类型
     * @param request 当前请求
     * @param response 当前响应
     * @return 包装后的返回值
     */
    @Override
    @Nullable
    @SneakyThrows
    public Object beforeBodyWrite(@Nullable Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (body instanceof Result<?>) {
            return body;
        }
        if (body instanceof String) {
            return objectMapper.writeValueAsString(Result.success(body));
        }
        return Result.success(body);
    }
}
