using OpenTool.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test.Core
{
    internal class DivideAction : AbstractAction<int>
    {
        public override async Task DoHandlerAsync(int context)
        {
            context /= 2;
            await Task.CompletedTask;
        }
    }
}