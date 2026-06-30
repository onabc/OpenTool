package cn.luxio.opentool.core.util;

/**
 * 字符工具类
 */
public class CharUtil {

    /**
     * 判断字符是否为空白字符
     *
     * @param c 待判断字符
     * @return 为空白字符返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isBlankChar(final char c) {
        return isBlankChar((int) c);
    }

    /**
     * 判断字符是否为非空白字符
     *
     * @param c 待判断字符
     * @return 为空白字符返回 {@code false}，否则返回 {@code true}
     */
    public static boolean isNotBlankChar(final char c) {
        return !isBlankChar((int) c);
    }

    /**
     * 判断 Unicode 码点是否为空白字符
     * <p>
     * 除 Java 标准空白字符外，也包含 BOM、零宽字符等常见不可见字符
     *
     * @param c 待判断 Unicode 码点
     * @return 为空白字符返回 {@code true}，否则返回 {@code false}
     */
    @SuppressWarnings("UnnecessaryUnicodeEscape")
    public static boolean isBlankChar(final int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a'
                || c == '\u0000'
                // issue#I5UGSQ，Hangul Filler
                || c == '\u3164'
                // Braille Pattern Blank
                || c == '\u2800'
                // Zero Width Non-Joiner, ZWNJ
                || c == '\u200c'
                // MONGOLIAN VOWEL SEPARATOR
                || c == '\u180e';
    }
}
