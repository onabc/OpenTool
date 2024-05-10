namespace OpenTool.Core.Util
{
    public static class StringUtil
    {
        /// <summary>
        /// 字符串是否为空白
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public static bool IsBlank(string str)
        {
            return string.IsNullOrWhiteSpace(str);
        }

        /// <summary>
        /// 字符串是否为非空白
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public static bool IsNotBlank(string str)
        {
            return !IsBlank(str);
        }
    }
}