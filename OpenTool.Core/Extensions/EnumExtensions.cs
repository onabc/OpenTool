﻿using System;
using System.ComponentModel;
using System.Linq;

namespace OpenTool.Core.Extensions
{
    /// <summary>
    /// 枚举扩展方法
    /// </summary>
    public static class EnumExtensions
    {
        /// <summary>
        /// 获取泛型枚举描述Description
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="enumValue"></param>
        /// <returns></returns>
        public static string GetEnumDescription<T>(this T enumValue) where T : Enum
        {
            string description = enumValue.ToString();
            try
            {
                description = (enumValue.GetType().GetField(enumValue.ToString())
                .GetCustomAttributes(typeof(DescriptionAttribute), inherit: true)
                .FirstOrDefault() as DescriptionAttribute)
                .Description;
            }
            catch (Exception)
            {
            }

            return description;
        }
    }
}