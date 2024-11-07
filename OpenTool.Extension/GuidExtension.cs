using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace OpenTool.Extension
{
    public static class GuidExtension
    {
        private const string GuidMatchPattern =
         "^[A-Fa-f0-9]{32}$|^({|\\()?[A-Fa-f0-9]{8}-([A-Fa-f0-9]{4}-){3}[A-Fa-f0-9]{12}(}|\\))?$|^({)?[0xA-Fa-f0-9]{3,10}(, {0,1}[0xA-Fa-f0-9]{3,6}){2}, {0,1}({)([0xA-Fa-f0-9]{3,4}, {0,1}){7}[0xA-Fa-f0-9]{3,4}(}})$";

        private static readonly Regex GuidMatchRegex = new Regex(GuidMatchPattern, RegexOptions.Compiled);

        public static bool TryParseGuid(this string str, out Guid result)
        {
            result = Guid.Empty;
            if (string.IsNullOrEmpty(str) || !GuidMatchRegex.IsMatch(str))
            {
                return false;
            }
            result = new Guid(str);
            return true;
        }

        public static bool TryExtractGuid(this string source, out Guid result)
        {
            result = Guid.Empty;
            if (string.IsNullOrEmpty(source))
                return false;

            var braceIndex = source.IndexOfAny(new[] { '{', '(' });
            if (braceIndex >= 0)
            {
                var endingBraceIndex = source.IndexOfAny(new[] { '}', ')' });
                source = endingBraceIndex > braceIndex
                    ? source.Substring(braceIndex, endingBraceIndex - braceIndex + 1)
                    : source.Substring(braceIndex + 1);
            }

            return TryParseGuid(source, out result);
        }
    }
}