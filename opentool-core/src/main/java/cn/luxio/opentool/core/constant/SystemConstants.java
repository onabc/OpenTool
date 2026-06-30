package cn.luxio.opentool.core.constant;

/**
 * 系统常量
 *
 * @author lukan3
 * @date 2025/01/08 00:00
 */
public interface SystemConstants {
    /**
     * 根id
     */
    Long ROOT_NODE_ID = 0L;

    /**
     * 系统默认密码
     */
    String DEFAULT_PASSWORD = "123456";

    /**
     * 认证令牌类型
     */
    String TOKEN_TYPE = "Bearer";

    /**
     * 超级管理员角色编码
     */
    String ROOT_ROLE_CODE = "ROOT";

    /**
     * 租户标识请求头
     */
    String TENANT_ID = "TenantId";
}
