using OpenTool.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test.Core
{
    internal class SubtractAction : AbstractAction<int>
    {
        public override async Task DoHandlerAsync(int context)
        {
            context -= 8;
            await Task.CompletedTask;
        }
    }
}