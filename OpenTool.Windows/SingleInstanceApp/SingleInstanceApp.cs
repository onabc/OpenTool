using System.Diagnostics;
using System.Windows;

namespace OpenTool.Windows
{
    public static class SingleInstanceApp
    {
        private static SingleInstance singleInstance;

        static SingleInstanceApp()
        {
            Configure();
        }

        private static void Configure()
        {
            string appTypeName = Application.Current.GetType().FullName;
            string identifier = @$"SingleInstance_{appTypeName}_PassArgumentsAsync";
            singleInstance = new SingleInstance(identifier);

            singleInstance.ArgumentsReceived.Subscribe(args =>
            {
                SingleInstance.ActivateWindow(Process.GetCurrentProcess());
            });
        }

        /// <summary>
        ///
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        /// <returns></returns>
        public static bool InitializeAsFirstInstance(object sender, StartupEventArgs e)
        {
            bool isFirstInstance = singleInstance.IsFirstInstance;
            if (!isFirstInstance)
            {
                singleInstance.PassArgumentsToFirstInstance(e.Args);
                if (sender is Application app) app.Shutdown();
            }
            else
            {
                singleInstance.ListenForArgumentsFromSuccessiveInstances();
            }
            return isFirstInstance;
        }
    }
}