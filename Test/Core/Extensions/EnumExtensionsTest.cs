using OpenTool.Core.Extensions;
using DescriptionAttribute = System.ComponentModel.DescriptionAttribute;

namespace Test.Core.Extensions
{
    public class EnumExtensionsTest
    {
        [Test]
        public void GetDescriptionTest()
        {
            Color color = Color.Red;
            string desc = color.GetEnumDescription();
            Assert.IsTrue(desc == "红色");
        }
    }

    public enum Color
    {
        [Description("红色")]
        Red,

        [Description("蓝色")]
        Blue
    }
}