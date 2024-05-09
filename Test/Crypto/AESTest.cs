using OpenTool.Crypto.Symmetric;

namespace Test.Crypto
{
    public class AESTest
    {
        private AES aes;

        [SetUp]
        public void Setup()
        {
            aes = new AES("0000000000000000", "0000000000000000");
        }

        [Test]
        public void Test1()
        {
            string data = "hello world";
            string encryptedStr = aes.EncryptToBase64(data);
            string decryptedStr = aes.DecryptFromBase64(encryptedStr);

            Assert.IsTrue(data.Equals(decryptedStr));
        }
    }
}