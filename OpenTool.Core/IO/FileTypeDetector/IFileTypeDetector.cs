using System.IO;

namespace OpenTool.Core.IO
{
    public interface IFileTypeDetector
    {
        /// <summary>
        /// 真实扩展名
        /// </summary>
        string Extension { get; }

        /// <summary>
        /// MimeType
        /// </summary>
        string MimeType { get; }

        /// <summary>
        /// 文件类型
        /// </summary>
        FileType FileType { get; }

        /// <summary>
        /// 校验
        /// </summary>
        /// <param name="stream"></param>
        /// <returns></returns>
        bool Detect(Stream stream);
    }
}