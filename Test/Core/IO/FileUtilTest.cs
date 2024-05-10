using OpenTool.Core.IO;

namespace Test.Core.IO
{
    public class FileUtilTest
    {
        [Test]
        public async Task GetFileType_Test()
        {
            string file = @"F:\1.png";
            var fileType = await FileUtil.GetFileTypeAsync(file);
        }
    }
}