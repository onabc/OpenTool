package cn.luxio.opentool.webapi.handler;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.core.response.ResultCode;
import cn.luxio.opentool.core.exception.BizException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.Set;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    public void handleBindExceptionShouldReturnFieldErrors() {
        BindException exception = new BindException(new UserForm(), "userForm");
        exception.rejectValue("name", "NotBlank", "不能为空");
        exception.rejectValue("age", "Min", "不能小于1");

        Result<?> result = handler.handleBindException(exception);

        assertEquals(false, result.getSuccess());
        assertEquals(ResultCode.PARAM_ERROR.getCode(), result.getCode());
        assertEquals("name:不能为空; age:不能小于1", result.getMessage());
    }

    @Test
    public void handleConstraintViolationExceptionShouldReturnMessage() {
        ConstraintViolationException exception = new ConstraintViolationException("参数不合法", Set.of());

        Result<?> result = handler.handleConstraintViolationException(exception);

        assertEquals(ResultCode.PARAM_ERROR.getCode(), result.getCode());
        assertEquals("参数不合法", result.getMessage());
    }

    @Test
    public void handleMissingServletRequestParameterExceptionShouldReturnParamIsNull() {
        MissingServletRequestParameterException exception =
                new MissingServletRequestParameterException("name", "String");

        Result<?> result = handler.handleMissingServletRequestParameterException(exception);

        assertEquals(ResultCode.PARAM_IS_NULL.getCode(), result.getCode());
    }

    @Test
    public void handleHttpMessageNotReadableExceptionShouldConvertFieldTypeMessage() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException(
                "JSON parse error",
                new IllegalArgumentException("Cannot deserialize value at [\"age\"]"),
                new MockHttpInputMessage(new byte[0]));

        Result<?> result = handler.handleHttpMessageNotReadableException(exception);

        assertEquals(ResultCode.ARGUMENT_ILLEGAL.getCode(), result.getCode());
        assertEquals("age字段类型错误", result.getMessage());
    }

    @Test
    public void handleHttpMessageNotReadableExceptionShouldReturnJsonErrorWhenCauseMissing() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException(
                "JSON parse error",
                new MockHttpInputMessage(new byte[0]));

        Result<?> result = handler.handleHttpMessageNotReadableException(exception);

        assertEquals(ResultCode.JSON_ERROR.getCode(), result.getCode());
        assertEquals(ResultCode.JSON_ERROR.getMessage(), result.getMessage());
    }

    @Test
    public void handleBizExceptionShouldUseResultCode() {
        Result<?> result = handler.handleBizException(new BizException(ResultCode.DATA_NOT_EXIST));

        assertEquals(ResultCode.DATA_NOT_EXIST.getCode(), result.getCode());
        assertEquals(ResultCode.DATA_NOT_EXIST.getMessage(), result.getMessage());
    }

    @Test
    public void handleBizExceptionShouldUseMessage() {
        Result<?> result = handler.handleBizException(new BizException("余额不足"));

        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertEquals("余额不足", result.getMessage());
    }

    @Test
    public void handleCompletionExceptionShouldUnwrapBizException() {
        CompletionException exception = new CompletionException(new BizException(ResultCode.DATA_IS_EXIST));

        Result<?> result = handler.handleCompletionException(exception);

        assertEquals(ResultCode.DATA_IS_EXIST.getCode(), result.getCode());
    }

    static class UserForm {

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
