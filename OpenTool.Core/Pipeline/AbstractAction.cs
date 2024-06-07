using System.Threading.Tasks;

namespace OpenTool.Core.Pipeline
{
    public abstract class AbstractAction<T>
    {
        public AbstractAction<T> Next { get; set; }

        public async Task ExecuteAsync(T context)
        {
            await DoHandlerAsync(context);

            if (Next != null)
            {
                await Next.ExecuteAsync(context);
            }
        }

        public abstract Task DoHandlerAsync(T context);
    }
}