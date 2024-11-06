using Autofac;
using Autofac.Builder;
using Autofac.Features.Scanning;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;

namespace OpenTool.Extension
{
    public static class AutofacExtension
    {
        /// <summary>
        /// 用于辅助批量注册，筛选继承于指定类型的子类。
        /// </summary>
        /// <typeparam name="T">指定类型的基类，所有继承与它的子类都会被包含</typeparam>
        public static IRegistrationBuilder<object, ScanningActivatorData, DynamicRegistrationStyle> RegisterAssignableAssemblyTypes<T>(this ContainerBuilder builder, IEnumerable<Assembly> assemblies)
        {
            if (assemblies is null)
            {
                throw new ArgumentNullException(nameof(assemblies));
            }

            return builder.RegisterAssemblyTypes(assemblies.ToArray())
                .AssignableTo<T>();
        }
    }
}