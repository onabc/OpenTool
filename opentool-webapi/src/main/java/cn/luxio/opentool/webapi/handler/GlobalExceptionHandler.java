package cn.luxio.opentool.webapi.handler;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.core.response.ResultCode;
import cn.luxio.opentool.core.exception.BizException;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.concurrent.CompletionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String ERROR_MESSAGE_SEPARATOR = "; ";
    private static final Pattern FIELD_PATH_PATTERN = Pattern.compile("\\[\"(.*?)\"]");

    /**
     * 处理表单参数绑定异常
     *
     * @param e 绑定异常
     * @return 统一失败响应
     */
    @ExceptionHandler(BindException.class)
    public Result<?> handleBindException(BindException e) {
        log.warn("BindException: {}", e.getMessage());
        return Result.fail(ResultCode.PARAM_ERROR.getCode(), joinFieldErrors(e.getFieldErrors()));
    }

    /**
     * 处理请求参数校验异常
     *
     * @param e 校验异常
     * @return 统一失败响应
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> handleConstraintViolationException(ConstraintViolationException e) {
        log.warn("ConstraintViolationException: {}", e.getMessage());
        return Result.fail(ResultCode.PARAM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理请求体参数校验异常
     *
     * @param e 校验异常
     * @return 统一失败响应
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("MethodArgumentNotValidException: {}", e.getMessage());
        return Result.fail(ResultCode.PARAM_ERROR.getCode(), joinFieldErrors(e.getBindingResult().getFieldErrors()));
    }

    /**
     * 处理接口不存在异常
     *
     * @param e 接口不存在异常
     * @return 统一失败响应
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.warn("NoHandlerFoundException: {}", e.getMessage());
        return Result.fail(ResultCode.API_NOT_EXIST);
    }

    /**
     * 处理必填请求参数缺失异常
     *
     * @param e 参数缺失异常
     * @return 统一失败响应
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn("MissingServletRequestParameterException: {}", e.getMessage());
        return Result.fail(ResultCode.PARAM_IS_NULL);
    }

    /**
     * 处理方法参数类型不匹配异常
     *
     * @param e 类型不匹配异常
     * @return 统一失败响应
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.warn("MethodArgumentTypeMismatchException: {}", e.getMessage());
        return Result.fail(ResultCode.PARAMETER_FORMAT_MISMATCH);
    }

    /**
     * 处理不支持的请求媒体类型
     *
     * @param e 媒体类型异常
     * @return 统一失败响应
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.warn("HttpMediaTypeNotSupportedException: {}", e.getMessage());
        return Result.fail(ResultCode.PARAM_ERROR.getCode(), "Content-Type类型错误");
    }

    /**
     * 处理不支持的请求方法
     *
     * @param e 请求方法异常
     * @return 统一失败响应
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.warn("HttpRequestMethodNotSupportedException: {}", e.getMessage());
        return Result.fail(ResultCode.PARAM_ERROR.getCode(), "请求方法错误");
    }

    /**
     * 处理 Servlet 异常
     *
     * @param e Servlet 异常
     * @return 统一失败响应
     */
    @ExceptionHandler(ServletException.class)
    public Result<?> handleServletException(ServletException e) {
        log.error("ServletException: {}", e.getMessage(), e);
        return Result.fail(ResultCode.SERVER_ERROR);
    }

    /**
     * 处理非法参数异常
     *
     * @param e 非法参数异常
     * @return 统一失败响应
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("IllegalArgumentException: {}", e.getMessage(), e);
        return Result.fail(ResultCode.ARGUMENT_ILLEGAL);
    }

    /**
     * 处理请求体不可读异常
     *
     * @param e 请求体不可读异常
     * @return 统一失败响应
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn("HttpMessageNotReadableException: {}", e.getMessage());
        String message = convertMessage(e.getCause());
        if (message == null || message.isBlank()) {
            return Result.fail(ResultCode.JSON_ERROR);
        }
        return Result.fail(ResultCode.ARGUMENT_ILLEGAL.getCode(), message);
    }

    /**
     * 处理类型转换异常
     *
     * @param e 类型转换异常
     * @return 统一失败响应
     */
    @ExceptionHandler(TypeMismatchException.class)
    public Result<?> handleTypeMismatchException(TypeMismatchException e) {
        log.warn("TypeMismatchException: {}", e.getMessage());
        return Result.fail(ResultCode.CONFIG_ERROR);
    }

    /**
     * 处理异步执行异常
     *
     * @param e 异步执行异常
     * @return 统一失败响应
     */
    @ExceptionHandler(CompletionException.class)
    public Result<?> handleCompletionException(CompletionException e) {
        log.warn("CompletionException: {}", e.getMessage());
        Throwable cause = e.getCause();
        if (cause instanceof BizException bizException) {
            return handleBizException(bizException);
        }
        return Result.fail(ResultCode.FEIGN_ERROR);
    }

    /**
     * 处理业务异常
     *
     * @param e 业务异常
     * @return 统一失败响应
     */
    @ExceptionHandler(BizException.class)
    public Result<?> handleBizException(BizException e) {
        log.warn("BizException: {}", e.getMessage());
        if (e.getResultCode() != null) {
            return Result.fail(e.getResultCode());
        }
        return Result.fail(e.getMessage());
    }

    /**
     * 处理兜底异常
     *
     * @param e 异常对象
     * @return 统一失败响应
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    private String joinFieldErrors(Iterable<FieldError> fieldErrors) {
        return java.util.stream.StreamSupport.stream(fieldErrors.spliterator(), false)
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .collect(Collectors.joining(ERROR_MESSAGE_SEPARATOR));
    }

    private String convertMessage(Throwable throwable) {
        if (throwable == null) {
            return null;
        }

        Matcher matcher = FIELD_PATH_PATTERN.matcher(throwable.toString());
        if (matcher.find()) {
            return matcher.group(1) + "字段类型错误";
        }
        return throwable.getMessage();
    }
}
