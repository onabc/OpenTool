using OpenTool.Core.Util;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace OpenTool.Extension
{
    public static class StringExtension
    {
        public static string SafeNormalize(this string input, NormalizationForm normalizationForm = NormalizationForm.FormC)
        {
            try
            {
                return StringUtil.ReplaceNonCharacters(input, '?').Normalize(normalizationForm);
            }
            catch (ArgumentException e)
            {
                throw new InvalidDataException("String contains invalid characters. Data: " + Encoding.UTF32.GetBytes(input).ToHexString(), e);
            }
        }

        public static string ToHexString(this byte[] ba)
        {
            var hex = BitConverter.ToString(ba);
            return hex.Replace("-", "");
        }

        public static bool ContainsAny(this string s, IEnumerable<string> items, StringComparison comparisonType)
        {
            return items.Any(item => s.IndexOf(item, comparisonType) >= 0);
        }

        public static bool ContainsAll(this string s, IEnumerable<string> items, StringComparison comparisonType)
        {
            return items.All(item => s.IndexOf(item, comparisonType) >= 0);
        }
    }
}