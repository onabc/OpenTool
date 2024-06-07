using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

namespace OpenTool.Core.Extensions
{
    public static class CollectionExtensions
    {
        public static bool IsNullOrEmpty(this IEnumerable source)
        {
            if (source != null)
            {
                return !source.GetEnumerator().MoveNext();
            }

            return true;
        }

        public static void AddMany<T>(this ICollection<T> source, params T[] items)
        {
            if (source is null) throw new ArgumentNullException(nameof(source));
            if (items is null) throw new ArgumentNullException(nameof(items));
            foreach (var ji in items)
            {
                source.Add(ji);
            }
        }

        public static void RemoveAll<T>(this ICollection<T> source, Func<T, bool> predicate)
        {
            var items = source.Where(predicate).ToList();

            foreach (var item in items)
            {
                source.Remove(item);
            }
        }
    }
}