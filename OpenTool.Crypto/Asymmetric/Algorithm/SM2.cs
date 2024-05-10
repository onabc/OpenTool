using OpenTool.Core.Extensions;
using Org.BouncyCastle.Asn1.GM;
using Org.BouncyCastle.Asn1.X9;
using Org.BouncyCastle.Crypto;
using Org.BouncyCastle.Crypto.Engines;
using Org.BouncyCastle.Crypto.Parameters;
using Org.BouncyCastle.Math;
using Org.BouncyCastle.Security;
using Org.BouncyCastle.Utilities.Encoders;

namespace OpenTool.Crypto.Asymmetric
{
    /// <summary>
    /// 国密SM2 非对称算法
    /// </summary>
    public class SM2
    {
        private string code = "04";
        private ICipherParameters _privateKey;
        private ICipherParameters _publicKey;
        private static X9ECParameters x9ECParameters = GMNamedCurves.GetByName("sm2p256v1");
        private static ECDomainParameters ecDomainParameters = new ECDomainParameters(x9ECParameters.Curve, x9ECParameters.G, x9ECParameters.N);
        private SM2Engine sm2Engine = new SM2Engine();

        /// <summary>
        ///
        /// </summary>
        /// <param name="privateKeyHex">十六进制的私钥</param>
        /// <param name="publicKeyHex">十六进制的公钥</param>
        public SM2(string privateKeyHex = "", string publicKeyHex = "")
        {
            if (!string.IsNullOrEmpty(publicKeyHex)) _publicKey = CreatePublicKey(publicKeyHex);
            if (!string.IsNullOrEmpty(privateKeyHex)) _privateKey = CreatePrivateKey(privateKeyHex);
        }

        public string DecryptFromHex(string str)
        {
            str = code + str;
            byte[] bytesToDecrypt = Hex.Decode(str);
            byte[] byDecrypted = DecryptOld(ChangeC1C3C2ToC1C2C3(bytesToDecrypt), _privateKey);
            return byDecrypted.BytesToString();
        }

        public string EncryptToHex(string str)
        {
            var encryptedBytes = ChangeC1C2C3ToC1C3C2(EncryptOld(str.StringToBytes(), _publicKey));
            string newCipherText = Hex.ToHexString(encryptedBytes);
            // .NET BC库SM2加密结果会带04，如果JAVA 那边报 Invalid point encoding 错误，删除加密结果前的04。
            // 如果对方要的是BASE64的加密结果，我们可以先转16进制字符串，裁掉04，再转BASE64。
            if (newCipherText.StartsWith(code)) newCipherText = newCipherText.Substring(2);
            return newCipherText;
        }

        private byte[] EncryptOld(byte[] data, ICipherParameters publicKey)
        {
            sm2Engine.Init(true, new ParametersWithRandom(publicKey, new SecureRandom()));
            return sm2Engine.ProcessBlock(data, 0, data.Length);
        }

        private byte[] DecryptOld(byte[] data, ICipherParameters privateKey)
        {
            sm2Engine.Init(false, privateKey);
            return sm2Engine.ProcessBlock(data, 0, data.Length);
        }

        private ICipherParameters CreatePublicKey(string publicKeyHex)
        {
            //公钥X，前64位
            string x = publicKeyHex.Substring(0, 64);
            //公钥Y，后64位
            string y = publicKeyHex.Substring(64);
            //获取公钥对象
            return new ECPublicKeyParameters(x9ECParameters.Curve.CreatePoint(new BigInteger(x, 16), new BigInteger(y, 16)), ecDomainParameters);
        }

        private ICipherParameters CreatePrivateKey(string privateKeyHex)
        {
            BigInteger d = new BigInteger(privateKeyHex, 16);
            //先拿到私钥对象，用ECPrivateKeyParameters 或 AsymmetricKeyParameter 都可以
            return new ECPrivateKeyParameters(d, ecDomainParameters);
        }

        /// <summary>
        /// bc加解密使用旧标c1||c2||c3，此方法在加密后调用，将结果转化为c1||c3||c2
        /// </summary>
        /// <param name="c1c2c3"></param>
        /// <returns></returns>
        private static byte[] ChangeC1C2C3ToC1C3C2(byte[] c1c2c3)
        {
            int c1Len = (x9ECParameters.Curve.FieldSize + 7) / 8 * 2 + 1; //sm2p256v1的这个固定65。可看GMNamedCurves、ECCurve代码。
            const int c3Len = 32; //new SM3Digest().getDigestSize();
            byte[] result = new byte[c1c2c3.Length];
            Buffer.BlockCopy(c1c2c3, 0, result, 0, c1Len); //c1
            Buffer.BlockCopy(c1c2c3, c1c2c3.Length - c3Len, result, c1Len, c3Len); //c3
            Buffer.BlockCopy(c1c2c3, c1Len, result, c1Len + c3Len, c1c2c3.Length - c1Len - c3Len); //c2
            return result;
        }

        /// <summary>
        /// bc加解密使用旧标c1||c3||c2，此方法在解密前调用，将密文转化为c1||c2||c3再去解密
        /// </summary>
        /// <param name="c1c3c2"></param>
        /// <returns></returns>
        private static byte[] ChangeC1C3C2ToC1C2C3(byte[] c1c3c2)
        {
            int c1Len = (x9ECParameters.Curve.FieldSize + 7) / 8 * 2 + 1; //sm2p256v1的这个固定65。可看GMNamedCurves、ECCurve代码。
            const int c3Len = 32; //new SM3Digest().GetDigestSize();
            byte[] result = new byte[c1c3c2.Length];
            Buffer.BlockCopy(c1c3c2, 0, result, 0, c1Len); //c1: 0->65
            Buffer.BlockCopy(c1c3c2, c1Len + c3Len, result, c1Len, c1c3c2.Length - c1Len - c3Len); //c2
            Buffer.BlockCopy(c1c3c2, c1Len, result, c1c3c2.Length - c3Len, c3Len); //c3
            return result;
        }
    }
}