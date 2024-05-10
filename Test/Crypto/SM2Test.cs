using OpenTool.Crypto.Asymmetric;

namespace Test.Crypto
{
    public class SM2Test
    {
        private string publicKey = "9EF573019D9A03B16B0BE44FC8A5B4E8E098F56034C97B312282DD0B4810AFC3CC759673ED0FC9B9DC7E6FA38F0E2B121E02654BF37EA6B63FAF2A0D6013EADF";
        private string privateKey = "FAB8BBE670FAE338C9E9382B9FB6485225C11A3ECB84C938F10F20A93B6215F0";
        private SM2 sm2;

        [SetUp]
        public void Setup()
        {
            sm2 = new SM2(privateKey, publicKey);
        }

        [Test]
        public void Test1()
        {
            string data = "hello world";
            string encryptedStr = sm2.EncryptToHex(data);
            string decryptedStr = sm2.DecryptFromHex(encryptedStr);

            Assert.IsTrue(data.Equals(decryptedStr));
        }
    }
}