package cn.luxio.opentool.core.util;

/**
 * 对象工具类
 */
public class ObjectUtil {

    /**
     * 判断对象是否为 {@code null}
     *
     * @param obj 待判断对象
     * @return 为 {@code null} 返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isNull(final Object obj) {
        return null == obj;
    }

    /**
     * 判断对象是否不为 {@code null}
     *
     * @param obj 待判断对象
     * @return 不为 {@code null} 返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isNotNull(final Object obj) {
        return null != obj;
    }
}
