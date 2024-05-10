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
        private string publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyhiTPNZY5DKa/ZTSbShoFhBV2egIjFDL1Urdt5rkQH4f/emWxjqO39jMxDDyRA8kz8b/QPuxISXiwomUaYFV9DJCERWYNLny1xZKXGYQwq2JWbaamNe0pdhk4gkYLHu//WDH96mesl99iO+lFoAW395CYN3xStUkyHcOGcQXj9eO6EzzLvqkhU7uX/tCbyzlz1uvxVaxMwAl8aOgMv6lesRol8z6E9oSMou4IRo2QjVI/tNHlwEWO6WmznVIQhQtP5Sv3MExLx3G1AEiIj3d2ja/FATcUbictddHMcPP+cn1vimfY+70Yv9rJDG4zDwHbniDhae/+k/8BWPKZCZYWQIDAQAB";
        private string privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDKGJM81ljkMpr9lNJtKGgWEFXZ6AiMUMvVSt23muRAfh/96ZbGOo7f2MzEMPJEDyTPxv9A+7EhJeLCiZRpgVX0MkIRFZg0ufLXFkpcZhDCrYlZtpqY17Sl2GTiCRgse7/9YMf3qZ6yX32I76UWgBbf3kJg3fFK1STIdw4ZxBeP147oTPMu+qSFTu5f+0JvLOXPW6/FVrEzACXxo6Ay/qV6xGiXzPoT2hIyi7ghGjZCNUj+00eXARY7pabOdUhCFC0/lK/cwTEvHcbUASIiPd3aNr8UBNxRuJy110cxw8/5yfW+KZ9j7vRi/2skMbjMPAdueIOFp7/6T/wFY8pkJlhZAgMBAAECggEAPL+uYrc7+TyNAnTd3BotT5SRefMZvrP60Z6zjyFsQ0ambzjsLaIkmH0e8EqWSIcU1bBHwkzmLcSpqTiZ+Cz8nW8OFPycyBytNqJor+WuXBa2FtWhGxicIM4vZRK+xYBdcdjJw1Slc5Dot2TyBxVxytGQunV8trXjXK8M+gnL21yzPa6KLTe8WmdRBpYrv7uAwGKMWy+OI2z1HCAkou4rqg5TEZLNWdb0oLjSmV0gkbydeYi0k+RNJyLq0do8bj249w9J0Zyx3xLSc5en4hPG1cmMvLHxEZCeCRBpXIB6wlIm+QIIRteMTwMNiz1mD0kG02EolODssd7ueHukQGLDQQKBgQD4ZC/pfzHqU7UYAVpplS1y4ZCC2HFaXeRR+DPMHUdi+GsbTuM5acSZb/FPe15LXgDA24ebF4eCNfC487Od1dErlpfqFo0Qbi8uRJtZky5eG1UmcaHX7QvryYIfRXysIS6mbgMgPWdBJ3Uu6dj2qT/wZQTju1RSY1Ww0kxQx8PvpQKBgQDQSVptfaabtLKw62ZW7fe/6YpY4K6JDt6xZM+DX1WpApvfjpfzVhSo8xTUbMSSsldeUXorD40b547AJ4RMzR+4+3nTBHFQk/Xnvu5/MWXGAHmABM0wUpBQvSe/BBzWkWH1m2dnEpJ4kPNvWJzVuV+1J2RlBtzXij9ezbQvLPPnpQKBgQDceyngTIxFIMplpYXYpJc0Gy7IRlHP0foCHJv1uaONIU8JbWvaoZzmSexcDfj0U1u91N+49hjNpw2nToMIPiwuUb1FB4FtrTBqFHIE/9UvqXla55CudvaW9EZxdZ+ltsqO5qYVrwuBVQH6g46X3EIqI7FEaBsACracfv9I0RmGFQKBgQC2RfskpjkPOerGeLpuiIGvKKkxbi5PMvwfMaGHzf4gkW/nC9pd0I7z6wXnf+fmmLtshtDqrglGyjVaYzqcG0YPk8Lgr5qacWFZFPWRyaDOzcuGBK2sBBTXEzGdL/Qssb2QOhcbVF1ptS/T2TAmuLrQxVBOONs6pHkgddOhFS29IQKBgEVGGQ0rfKwZVL4H4RW6AwYWPcPfjB/gk+qvuzO/8S8+UOWAoujEvUoBZ8KBUujtIgsLNV5n8N+hkcL1U05KAMjp3/0wl4hru8S2WzYnuaMcJa7RwH0ssi1vp5I24cmFx6Tyh1V+n8J0oZpJehJ0zWyo2R20IILr/CJWzYQO7kXd";
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
            RSA.CreateKeys(out string privateKey, out string publicKey);
        }
    }
}