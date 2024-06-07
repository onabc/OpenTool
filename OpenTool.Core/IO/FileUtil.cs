using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OpenTool.Core.IO
{
    public static class FileUtil
    {
        /// <summary>
        /// 打开文件夹
        /// </summary>
        /// <param name="path"></param>
        public static void OpenDirectory(string path)
        {
            try
            {
                Process.Start("explorer.exe", path);
            }
            catch (Exception)
            {
                throw;
            }
        }
    }
}