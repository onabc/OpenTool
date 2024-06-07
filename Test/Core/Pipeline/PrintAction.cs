using OpenTool.Core.Pipeline;
using System.Diagnostics;

namespace Test.Core
{
    internal class PrintAction : AbstractAction<int>
    {
        public override async Task DoHandlerAsync(int context)
        {
            Debug.WriteLine(context);
            await Task.CompletedTask;
        }
    }
}