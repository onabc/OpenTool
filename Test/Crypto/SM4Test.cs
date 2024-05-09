using OpenTool.Crypto.Symmetric;

namespace Test.Crypto
{
    public class SM4Test
    {
        private SM4 sm4;

        [SetUp]
        public void Setup()
        {
            sm4 = new SM4("0000000000000000", "0000000000000000");
        }

        [Test]
        public void Test1()
        {
            string data = "hello world";
            string encryptedStr = sm4.EncryptToBase64(data);
            string decryptedStr = sm4.DecryptFromBase64(encryptedStr);

            Assert.IsTrue(data.Equals(decryptedStr));
        }
    }
}