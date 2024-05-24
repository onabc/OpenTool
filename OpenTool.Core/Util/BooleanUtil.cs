using System.Collections.Immutable;

namespace OpenTool.Core.Util
{
    public static class BooleanUtil
    {
        private static readonly IEnumerable<string> TrueList = new List<string> { "true", "yes", "y", "t", "ok", "1", "on", "是", "对", "真", "對", "√" };
        private static readonly IEnumerable<string> FalseList = new List<string> { "false", "no", "n", "f", "0", "off", "否", "错", "假", "錯", "×" };

        /// <summary>
        /// 转换字符串为boolean值
        /// </summary>
        /// <param name="valueStr">字符串</param>
        /// <returns>boolean值</returns>
        public static bool ToBoolean(string valueStr)
        {
            if (StringUtil.IsNotBlank(valueStr))
            {
                valueStr = valueStr.Trim().ToLower();
                return TrueList.Contains(valueStr);
            }
            return false;
        }
    }
}