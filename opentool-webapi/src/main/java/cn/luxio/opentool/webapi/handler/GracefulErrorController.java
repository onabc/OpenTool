package cn.luxio.opentool.webapi.handler;

import cn.luxio.opentool.core.response.ResultCode;
import cn.luxio.opentool.webapi.annotation.IgnoreResponseAdvice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一错误控制器
 */
@Controller
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class GracefulErrorController extends BasicErrorController {

    /**
     * 创建统一错误控制器
     *
     * @param errorAttributes 错误属性
     * @param errorViewResolvers 错误视图解析器
     */
    public GracefulErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, new ErrorProperties(), errorViewResolvers);
    }

    /**
     * 输出统一错误响应
     *
     * @param request 当前请求
     * @return 错误响应
     */
    @Override
    @IgnoreResponseAdvice
    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        ResultCode resultCode = switch (status) {
            case NOT_FOUND -> ResultCode.API_NOT_EXIST;
            default -> ResultCode.FAILED;
        };

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("success", false);
        responseBody.put("code", resultCode.getCode());
        responseBody.put("message", resultCode.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseBody, status);
    }
}
