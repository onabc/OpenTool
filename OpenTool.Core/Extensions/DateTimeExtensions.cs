using System;
using System.Text;

namespace OpenTool.Core.Extensions
{
    /// <summary>
    /// 日期扩展方法
    /// </summary>
    public static class DateTimeExtensions
    {
        /// <summary>
        /// 计算相差天数
        /// </summary>
        /// <param name="beginTime"></param>
        /// <param name="endTime"></param>
        /// <returns></returns>
        public static int GetDiffDays(this in DateTime beginTime, in DateTime endTime)
        {
            TimeSpan timeSpan = endTime.Subtract(beginTime);
            return (int)timeSpan.TotalDays;
        }

        /// <summary>
        /// 计算时间差
        /// </summary>
        /// <param name="beginTime"></param>
        /// <param name="endTime"></param>
        /// <returns></returns>
        public static string GetDiffTime(this in DateTime beginTime, in DateTime endTime)
        {
            StringBuilder @string = new StringBuilder();
            TimeSpan timeSpan = endTime.Subtract(beginTime);
            if (timeSpan.Days >= 1) @string.Append(timeSpan.Days).Append("天");
            if (timeSpan.Hours >= 1) @string.Append(timeSpan.Hours).Append("小时");
            if (timeSpan.Minutes >= 1) @string.Append(timeSpan.Minutes).Append("分");
            if (timeSpan.Seconds >= 1) @string.Append(timeSpan.Seconds).Append("秒");
            return @string.ToString();
        }
    }
}