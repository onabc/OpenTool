using System.Threading.Tasks;
using System.Threading.Tasks.Dataflow;

namespace OpenTool.Core
{
    public class AbstractPipeline<T>
    {
        private ActionBlock<T> _actionBlock;
        private readonly AbstractAction<T> _action;

        public AbstractPipeline(params AbstractAction<T>[] actions)
        {
            _action = BuildAction(actions);
            _actionBlock = new ActionBlock<T>(_action.ExecuteAsync);
        }

        public AbstractPipeline(int limit, params AbstractAction<T>[] actions)
        {
            _action = BuildAction(actions);
            _actionBlock = new ActionBlock<T>(_action.ExecuteAsync,
                new ExecutionDataflowBlockOptions
                {
                    MaxDegreeOfParallelism = limit
                });
        }

        public virtual async Task HandleAsync(T context)
        {
            await _actionBlock.SendAsync(context);
        }

        private AbstractAction<T> BuildAction(params AbstractAction<T>[] actions)
        {
            var actionBuilder = new ActionBuilder<T>();
            foreach (var action in actions) actionBuilder.AddHandler(action);
            return actionBuilder.Build();
        }
    }
}