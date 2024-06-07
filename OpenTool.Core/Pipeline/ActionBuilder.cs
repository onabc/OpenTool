namespace OpenTool.Core.Pipeline
{
    public class ActionBuilder<T>
    {
        private AbstractAction<T> head;
        private AbstractAction<T> tail;

        public ActionBuilder<T> AddHandler(AbstractAction<T> handler)
        {
            if (head == null)
            {
                head = handler;
            }
            else
            {
                tail.Next = handler;
            }
            tail = handler;
            return this;
        }

        public AbstractAction<T> Build()
        {
            return head;
        }
    }
}