using OpenTool.Core.Extensions;
using OpenTool.Crypto.Asymmetric;
using Org.BouncyCastle.Crypto.Parameters;
using Org.BouncyCastle.Security;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test.Crypto
{
    public class RSATest
    {
        private string publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/Lr3oyc+rxFXbz2dvaoMI6N0RmlijWYB8+g11hWvhzeQaWk6RYeaPPdqJoe/6qVnDp2vx5m7n7afo6azZHg3turQoyvx8dnIl6JaIGx9DmPz2l92cCJjR09qSJ/TCQ+mnXKJVrbDmqC9aqp72PdtBn1uVrANBIox9o3tqdVWqMQIDAQAB";
        private string privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL8uvejJz6vEVdvPZ29qgwjo3RGaWKNZgHz6DXWFa+HN5BpaTpFh5o892omh7/qpWcOna/Hmbuftp+jprNkeDe26tCjK/Hx2ciXologbH0OY/PaX3ZwImNHT2pIn9MJD6adcolWtsOaoL1qqnvY920GfW5WsA0EijH2je2p1VaoxAgMBAAECgYAftOvQELA9Z3lp1BLuenay4pPPWI68wJo7n+jdhbwnndLnv/RpzhfDx9lXnCDFrJJzjW+sla/tDRAc46xD8eUTL1vUstTIWKdV/Fb1wyDDHUKAdF/sVPt4WqrjFex7svKcdVt6x3mdBha9/dVBIK7jEOl6KIXxb5Nk7cnyHGRoQQJBAPecQKo6jhnzmqUJ8LppTfD5a8fcGS6G9eQ2NugZGIC1UqrQzXjmzlvTnjGoC9ZJVxS5Z35frRctgFoh1D3vxkkCQQDFqQpVz8a2AQmQW4cNplaNwtMfs3x4nHZA6yPDM3RzkEvu/NfdQaOrcr7um2mL3XsXqE10+rPgS9QohQt63aSpAkEA2DuL/RzFm8Qw+I+FukTb2+T1SsPa+dbCRVlS+b1wuHWgi6tsxHhLHcXgEF1AeRHzChYfWy0Sa3tA63U/dBdAgQJATxeE+zFY4hsoKIt3Tw7wM2lx8Y1wkRKKfw+YD9PXFSb4O3kRJ4fMZh5UKEYnxb+qMZ024UgwEeBF1LDoFxH5gQJBAOr7dBjCNxmu1LthVIJDBPuMnGCppz+PpOgFphZFpISXi9QgavU/dBO7glkA2tuYWD2GI4rB6x0Bnm2fJRU48ws=";
        private RSA rsa;

        [SetUp]
        public void Setup()
        {
            rsa = new RSA(privateKey: privateKey, publicKey: publicKey);
        }

        [Test]
        public void Test1()
        {
            string data = "hello world";
            string encryptedStr = rsa.EncryptToBase64(data, KeyType.PublicKey);
            string decryptedStr = rsa.DecryptFromBase64(encryptedStr, KeyType.PrivateKey);

            Assert.IsTrue(data.Equals(decryptedStr));
        }

        [Test]
        public void Test2()
        {
            string data = "hello world";
            string encryptedStr = rsa.EncryptToBase64(data, KeyType.PrivateKey);
            string decryptedStr = rsa.DecryptFromBase64(encryptedStr, KeyType.PublicKey);

            Assert.IsTrue(data.Equals(decryptedStr));
        }

        [Test]
        public void Test3()
        {
            rsa.CreateKeys(out string privateKey, out string publicKey);
        }
    }
}