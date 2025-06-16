using System;
using System.Collections.Generic;

namespace OpenTool.Extension
{
    public static class IEnumerableExtensions
    {
        /// <summary>
        /// Select using the given action, but ignore exceptions and skip offending items.
        /// </summary>
        public static IEnumerable<TOut> Attempt<TIn, TOut>(this IEnumerable<TIn> baseEnumerable,
            Func<TIn, TOut> action)
        {
            foreach (var item in baseEnumerable)
            {
                TOut output;
                try
                {
                    output = action(item);
                }
                catch (Exception e)
                {
                    Console.Error.WriteLine("Attempt failed, skipping. Error: " + e);
                    continue;
                }
                yield return output;
            }
        }
    }
}