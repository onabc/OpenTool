using System.ComponentModel;

namespace OpenTool.Core.IO
{
    public enum FileType
    {
        [Description("图片")]
        Image,

        [Description("视频")]
        Video,

        [Description("音频")]
        Audio,

        [Description("压缩包")]
        Compression,

        [Description("文档")]
        Document,
    }
}