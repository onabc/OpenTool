using System.Text;

namespace OpenTool.Core.Extensions
{
    public static class StringExtensions
    {
        public static byte[] StringToBytes(this string str)
        {
            return Encoding.UTF8.GetBytes(str);
        }

        public static byte[] Base64ToBytes(this string str)
        {
            return Convert.FromBase64String(str);
        }

        public static byte[] HexToBytes(this string str)
        {
            byte[] arrByte = new byte[str.Length / 2];
            int index = 0;
            for (int i = 0; i < str.Length; i += 2)
            {
                arrByte[index++] = Convert.ToByte(str.Substring(i, 2), 16);
            }
            return arrByte;
        }
    }
}