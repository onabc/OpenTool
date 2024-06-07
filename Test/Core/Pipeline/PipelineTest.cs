using OpenTool.Core.Pipeline;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test.Core.Pipeline
{
    internal class PipelineTest
    {
        private AbstractPipeline<int> pipeline;

        [SetUp]
        public void Init()
        {
            pipeline = new AbstractPipeline<int>(
                new AddAction(),
                new SubtractAction(),
                new MultiplyAction(),
                new DivideAction(),
                new PrintAction()
                );
        }

        [Test]
        public async Task ActionTest()
        {
            await pipeline.HandleAsync(5);
            await Task.Delay(5000);
        }
    }
}