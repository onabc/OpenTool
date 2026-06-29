package cn.luxio.opentool.core.response;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 响应结构体
 * @param <T> data包装类型
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    /**
     * 响应是否成功
     */
    private Boolean success;

    /**
     * 响应状态码
     */
    private String code;

    /**
     * 响应错状态信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功响应
     *
     * @return Result<T> 响应结构
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 成功响应
     *
     * @param data 响应内容
     * @return Result<T> 响应结构
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true)
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(ResultCode.SUCCESS.getMessage())
                .setData(data);
        return result;
    }

    /**
     * 失败响应
     *
     * @return Result 响应结构
     */
    public static Result<?> fail(ResultCode resultCode) {
        return fail(resultCode.getCode(), resultCode.getMessage());
    }

    /**
     * 失败响应
     *
     * @return Result 响应结构
     */
    public static <T> Result<T> fail(String message) {
        return fail(ResultCode.FAILED.getCode(), message);
    }

    /**
     * 失败响应
     *
     * @return Result 响应结构
     */
    public static <T> Result<T> fail(String code, String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false)
                .setCode(code)
                .setMessage(message);
        return result;
    }
}
