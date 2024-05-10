using OpenTool.Core.Extensions;
using System.Text.RegularExpressions;

namespace OpenTool.Core.IO
{
    public class FileUtil
    {
        /// <summary>
        /// 获取文件类型
        /// </summary>
        /// <param name="file">文件地址</param>
        /// <returns>文件类型</returns>
        public static async Task<FileType> GetFileTypeAsync(string file)
        {
            if (!IsExists(file)) throw new FileNotFoundException(nameof(file));
            using var stream = File.OpenRead(file);
            var buffer = new byte[0x85];
            await stream.ReadAsync(buffer, 0, buffer.Length);
            string header = buffer.BytesToHexString();

            var headerData = HeaderData.Map.FirstOrDefault(x => Regex.IsMatch(header, x.Pattern, RegexOptions.IgnoreCase | RegexOptions.IgnorePatternWhitespace));
            return headerData != null ? headerData.Type : FileType.UnKnown;
        }

        /// <summary>
        /// 文件是否存在
        /// </summary>
        /// <param name="file"></param>
        /// <returns></returns>
        public static bool IsExists(string file)
        {
            return File.Exists(file);
        }
    }
}