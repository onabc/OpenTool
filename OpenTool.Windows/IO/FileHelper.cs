using Microsoft.Win32;

namespace OpenTool.Windows
{
    public static class FileHelper
    {
        /// <summary>
        /// 浏览目录，并返回选择的文件。
        /// </summary>
        /// <param name="filter"><see cref="OpenFileDialog.Filter"/></param>
        /// <param name="multiSelect"><see cref="OpenFileDialog.Multiselect"/></param>
        /// <returns>选择的文件</returns>
        public static List<string> OpenFileDialog(string filter = "", bool multiSelect = false)
        {
            List<string> files = new List<string>();

            var dlg = new OpenFileDialog
            {
                Filter = filter,
                Multiselect = multiSelect
            };

            if (dlg.ShowDialog() == true)
            {
                files.AddRange(dlg.FileNames);
            }
            return files;
        }
    }
}