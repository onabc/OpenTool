package cn.luxio.opentool.core.exception;

import cn.luxio.opentool.core.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 业务异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4138930081529875681L;

    /**
     * 响应错误码
     */
    private ResultCode resultCode;

    /**
     * 创建带响应错误码的业务异常
     *
     * @param resultCode 响应错误码
     */
    public BizException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    /**
     * 创建带异常信息的业务异常
     *
     * @param message 异常信息
     */
    public BizException(String message) {
        super(message);
    }

    /**
     * 创建带异常信息和原因的业务异常
     *
     * @param message 异常信息
     * @param cause 异常原因
     */
    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 创建带异常原因的业务异常
     *
     * @param cause 异常原因
     */
    public BizException(Throwable cause) {
        super(cause);
    }
}
