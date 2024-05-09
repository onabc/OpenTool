namespace OpenTool.Core.Extensions
{
    public static class ArrayExtensions
    {
        /// <summary>
        /// 生成一个新的重新设置大小的数组
        /// </summary>
        /// <remarks>调整大小后拷贝原数组到新数组下。扩大则占位前N个位置，缩小则截断</remarks>
        /// <typeparam name="T">数组元素类型</typeparam>
        /// <param name="array">原数组</param>
        /// <param name="newSize">新的数组大小</param>
        /// <returns>调整后的新数组</returns>
        /// <exception cref="ArgumentNullException"></exception>
        public static T[] Resize<T>(this T[] array, int newSize)
        {
            if (array == null) throw new ArgumentNullException("array");
            if (newSize < 0)
            {
                return array;
            }
            T[] newArray = NewArray<T>(newSize);
            Array.Copy(array, 0, newArray, 0, Math.Min(array.Length, newSize));
            return newArray;
        }

        /// <summary>
        ///  新建一个空数组
        /// </summary>
        /// <typeparam name="T">数组元素类型</typeparam>
        /// <param name="newSize">数组大小</param>
        /// <returns>空数组</returns>
        public static T[] NewArray<T>(int newSize)
        {
            return (T[])Array.CreateInstance(typeof(T), newSize);
        }
    }
}