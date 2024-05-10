using OpenTool.Crypto.Digest;

namespace Test.Crypto
{
    public class MD5Test
    {
        private MD5 md5;

        [SetUp]
        public void Setup()
        {
            md5 = new MD5();
        }

        [Test]
        public void Test1()
        {
            string data = "123456";
            string digestedStr = md5.Digest(data);
            Assert.IsTrue("e10adc3949ba59abbe56e057f20f883e".Equals(digestedStr));
        }
    }
}