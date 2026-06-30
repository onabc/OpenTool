package cn.luxio.opentool.webapi.handler;

import cn.luxio.opentool.core.response.ResultCode;
import jakarta.servlet.RequestDispatcher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GracefulErrorControllerTest {

    @Test
    public void errorShouldReturnApiNotExistWhenStatusIsNotFound() {
        GracefulErrorController controller = new GracefulErrorController(new DefaultErrorAttributes(), List.of());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value());

        ResponseEntity<Map<String, Object>> response = controller.error(request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse((Boolean) response.getBody().get("success"));
        assertEquals(ResultCode.API_NOT_EXIST.getCode(), response.getBody().get("code"));
        assertEquals(ResultCode.API_NOT_EXIST.getMessage(), response.getBody().get("message"));
        assertNotNull(response.getBody().get("timestamp"));
    }

    @Test
    public void errorShouldReturnFailedWhenStatusIsNotSpecial() {
        GracefulErrorController controller = new GracefulErrorController(new DefaultErrorAttributes(), List.of());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());

        ResponseEntity<Map<String, Object>> response = controller.error(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ResultCode.FAILED.getCode(), response.getBody().get("code"));
    }
}
