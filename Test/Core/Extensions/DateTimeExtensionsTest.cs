using OpenTool.Core.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test.Core.Extensions
{
    public class DateTimeExtensionsTest
    {
        private DateTime _beginTime;
        private DateTime _endTime;

        [SetUp]
        public void Init()
        {
            _beginTime = DateTime.Parse("2010-05-27");
            _endTime = DateTime.Now;
        }

        [Test]
        public void GetDiffDaysTest()
        {
            int days = _beginTime.GetDiffDays(_endTime);
            string diff = _beginTime.GetDiffTime(_endTime);
        }
    }
}