namespace OpenTool.Core.Util
{
    public static class CharUtil
    {
        /// <summary>
        /// 检查字符是否为小写字母，小写字母指a~z
        /// </summary>
        /// <param name="ch"></param>
        /// <returns></returns>
        public static bool IsLower(char ch)
        {
            return char.IsLower(ch);
        }

        /// <summary>
        /// 判断是否为大写字母，大写字母包括A~Z
        /// </summary>
        /// <param name="ch"></param>
        /// <returns></returns>
        public static bool IsUpper(char ch)
        {
            return char.IsUpper(ch);
        }

        /// <summary>
        /// 检查是否为数字字符，数字字符指0~9
        /// </summary>
        /// <param name="ch"></param>
        /// <returns></returns>
        public static bool IsNumber(char ch)
        {
            return char.IsNumber(ch);
        }

        /// <summary>
        /// 是否空白符
        /// </summary>
        /// <param name="c"></param>
        /// <returns></returns>
        public static bool IsBlank(char c)
        {
            return char.IsWhiteSpace(c)
                    || c == '\ufeff'
                    || c == '\u202a'
                    || c == '\u0000'
                    // issue#I5UGSQ，Hangul Filler
                    || c == '\u3164'
                    // Braille Pattern Blank
                    || c == '\u2800'
                    // MONGOLIAN VOWEL SEPARATOR
                    || c == '\u180e';
        }
    }
}