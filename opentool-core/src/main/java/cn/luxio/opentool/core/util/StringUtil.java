package cn.luxio.opentool.core.util;

/**
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否为空白
     * <p>
     * {@code null}、空字符串以及只包含空白字符的字符串均视为空白
     *
     * @param str 待判断字符串
     * @return 为空白返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isBlank(final CharSequence str) {
        final int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (CharUtil.isNotBlankChar(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否为非空白
     *
     * @param str 待判断字符串
     * @return 非空白返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isNotBlank(final CharSequence str) {
        final int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (CharUtil.isNotBlankChar(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断字符串是否为空
     * <p>
     * {@code null} 或长度为 0 的字符串视为空
     *
     * @param str 待判断字符串
     * @return 为空返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isEmpty(final CharSequence str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否为非空
     *
     * @param str 待判断字符串
     * @return 非空返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isNotEmpty(final CharSequence str) {
        return !isEmpty(str);
    }
}
