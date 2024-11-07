using System.Text;

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

        public static string ReplaceNonCharacters(string input, char replacement)
        {
            var sb = new StringBuilder(input.Length);
            for (var i = 0; i < input.Length; i++)
            {
                if (char.IsSurrogatePair(input, i))
                {
                    int c = char.ConvertToUtf32(input, i);
                    i++;
                    if (IsValidCodePoint(c))
                        sb.Append(char.ConvertFromUtf32(c));
                    else
                        sb.Append(replacement);
                }
                else
                {
                    char c = input[i];
                    if (IsValidCodePoint(c))
                        sb.Append(c);
                    else
                        sb.Append(replacement);
                }
            }
            return sb.ToString();
        }

        private static bool IsValidCodePoint(int point)
        {
            return point < 0xfdd0 || point >= 0xfdf0 && (point & 0xffff) != 0xffff && (point & 0xfffe) != 0xfffe && point <= 0x10ffff;
        }
    }
}