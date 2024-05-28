using System;
using System.Text;

namespace OpenTool.Core.Extensions
{
    public static class ByteExtensions
    {
        public static string BytesToString(this byte[] bytes)
        {
            return Encoding.UTF8.GetString(bytes);
        }

        /// <summary>
        /// 将字节数组转换为十六进制字符串
        /// </summary>
        /// <param name="bytes">字节数组</param>
        /// <returns>十六进制字符串</returns>
        public static string BytesToHexString(this byte[] bytes)
        {
            StringBuilder hexString = new StringBuilder(bytes.Length * 2);
            foreach (byte b in bytes)
            {
                hexString.AppendFormat("{0:x2}", b);
            }
            return hexString.ToString();
        }

        /// <summary>
        /// 将字节数组转换为Base64字符串
        /// </summary>
        /// <param name="bytes">字节数组</param>
        /// <returns>Base64字符串</returns>
        public static string BytesToBase64String(this byte[] bytes)
        {
            return Convert.ToBase64String(bytes);
        }
    }
}