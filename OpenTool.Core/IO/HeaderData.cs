using static OpenTool.Core.IO.FileType;

namespace OpenTool.Core.IO
{
    internal class HeaderData
    {
        /// <summary>
        /// 正则-文件类型集合
        /// </summary>
        public static List<HeaderData> Map = new()
        {
            /* jpg | jpg | png | bmp | img | img | img | pic | gif | tif */
            (@"(00 00 00 0C 6A 50 20 20 0D 0A)
                |(FF D8 FF)

                |(89 50 4E 47)

                |(42 4D)

                |(00 01 00 08 00 01 00 01 01)
                |(50 49 43 54 00 08)
                |(53 43 4D 49)

                |(01 00 00 00 01)

                |(47 49 46 38)

                |(49 44 33)
                |(49 49 2A 00)
                |(4D 4D 00 2A)
                |(4D 4D 00 2B)"
            , Image),

            /* flv | 3gp | mp4 | mp4 | mp4 | mp4 | avi | mkv | mov | mpg | wmf | rm */
            (@"(46 4C 56 01)

                |(00 00 00 \S{2} 66 74 79 70 33 67 70)

                |(00 00 00 \S{2} 66 74 79 70)
                |(66 74 79 70 33 67 70 35)
                |(66 74 79 70 4D 53 4E 56)
                |(66 74 79 70 69 73 6F 6D)

                |(52 49 46 46 \S{8} 41 56 49 20 4C 49 53 54)

                |(1A 45 DF A3)

                |(6D 6F 6F 76)
                |(66 74 79 70 71 74 20 20)
                |(00 00 00 20 66 74 79 70 4D 34 41 20 00 00 00 00)
                |(66 72 65 65)
                |(6D 64 61 74)
                |(77 69 64 65)
                |(70 6E 6F 74)
                |(73 6B 69 70)
                |(\S{8} 6D 6F 6F 76)
                |(\S{8} 66 74 79 70)

                |(00 00 01 B\S)
                |(00 00 00 \S{2} 66)
                |(01 00 09 00 00 03)
                |(30 26 B2 75 8E 66 CF 11 A6 D9 00 AA 00 62 CE 6C)

                |(2E 52 4D 46)"
            , Video)
        };

        /// <summary>
        /// 正则
        /// </summary>
        public string Pattern { get; set; }

        /// <summary>
        /// 文件类型
        /// </summary>
        public FileType Type { get; set; }

        private HeaderData(string pattern, FileType type)
        {
            Pattern = pattern;
            Type = type;
        }

        public static implicit operator HeaderData((string, FileType) arguments)
        {
            return new HeaderData(arguments.Item1, arguments.Item2);
        }
    }
}