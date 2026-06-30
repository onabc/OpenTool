package cn.luxio.opentool.webapi.handler;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.core.response.ResultCode;
import cn.luxio.opentool.webapi.annotation.IgnoreResponseAdvice;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GracefulResponseAdviceTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ResponseBodyAdvice<Object> advice = new GracefulResponseAdvice(objectMapper);

    @Test
    public void supportsShouldReturnTrueWhenMethodNotIgnored() throws Exception {
        MethodParameter returnType = returnType(SampleController.class, "object");

        boolean supports = advice.supports(returnType, StringHttpMessageConverter.class);

        assertTrue(supports);
    }

    @Test
    public void supportsShouldReturnFalseWhenMethodIgnored() throws Exception {
        MethodParameter returnType = returnType(SampleController.class, "ignored");

        boolean supports = advice.supports(returnType, StringHttpMessageConverter.class);

        assertFalse(supports);
    }

    @Test
    public void supportsShouldReturnFalseWhenClassIgnored() throws Exception {
        MethodParameter returnType = returnType(IgnoredController.class, "object");

        boolean supports = advice.supports(returnType, StringHttpMessageConverter.class);

        assertFalse(supports);
    }

    @Test
    public void beforeBodyWriteShouldWrapObject() throws Exception {
        WriteResult writeResult = beforeBodyWrite(Map.of("name", "luxio"));

        Result<?> result = assertInstanceOf(Result.class, writeResult.body());
        assertTrue(result.getSuccess());
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertEquals(Map.of("name", "luxio"), result.getData());
        assertEquals(MediaType.APPLICATION_JSON, writeResult.contentType());
    }

    @Test
    public void beforeBodyWriteShouldKeepResult() throws Exception {
        Result<String> source = Result.success("ok");

        Object body = beforeBodyWrite(source).body();

        assertSame(source, body);
    }

    @Test
    public void beforeBodyWriteShouldSerializeStringResult() throws Exception {
        Object body = beforeBodyWrite("hello").body();

        JsonNode jsonNode = objectMapper.readTree((String) body);

        assertTrue(jsonNode.get("success").asBoolean());
        assertEquals(ResultCode.SUCCESS.getCode(), jsonNode.get("code").asText());
        assertEquals("hello", jsonNode.get("data").asText());
    }

    private WriteResult beforeBodyWrite(Object body) throws Exception {
        ServletServerHttpResponse response =
                new ServletServerHttpResponse(new MockHttpServletResponse());
        Object result = advice.beforeBodyWrite(body,
                returnType(SampleController.class, "object"),
                MediaType.APPLICATION_JSON,
                StringHttpMessageConverter.class,
                new org.springframework.http.server.ServletServerHttpRequest(new MockHttpServletRequest()),
                response);
        return new WriteResult(result, response.getHeaders().getContentType());
    }

    private MethodParameter returnType(Class<?> controllerClass, String methodName) throws Exception {
        Method method = controllerClass.getDeclaredMethod(methodName);
        return new MethodParameter(method, -1);
    }

    private record WriteResult(Object body, MediaType contentType) {
    }

    @RestController
    static class SampleController {

        @GetMapping
        Object object() {
            return null;
        }

        @IgnoreResponseAdvice
        @GetMapping
        Object ignored() {
            return null;
        }
    }

    @IgnoreResponseAdvice
    @RestController
    static class IgnoredController {

        @GetMapping
        Object object() {
            return null;
        }
    }
}
