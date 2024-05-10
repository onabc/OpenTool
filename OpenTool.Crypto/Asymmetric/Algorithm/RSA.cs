using OpenTool.Crypto.Enum;
using Org.BouncyCastle.Crypto;
using Org.BouncyCastle.Crypto.Generators;
using Org.BouncyCastle.OpenSsl;
using Org.BouncyCastle.Security;
using AsymmetricAlgorithm = OpenTool.Crypto.Enum.AsymmetricAlgorithm;

namespace OpenTool.Crypto.Asymmetric
{
    /// <summary>
    /// RSA 非对称算法
    /// </summary>
    public class RSA : AsymmetricCrypto
    {
        protected override AsymmetricAlgorithm Algorithm => AsymmetricAlgorithm.RSA;

        public RSA(string privateKey = "", string publicKey = "")
            : this(Mode.ECB, Padding.PKCS1Padding, privateKey, publicKey)
        {
        }

        public RSA(Mode mode, Padding padding, string privateKey, string publicKey)
            : base(mode, padding, privateKey, publicKey)
        {
        }

        /// <summary>
        /// 产生密钥
        /// </summary>
        /// <param name="xmlPrivateKey">私钥</param>
        /// <param name="xmlPublicKey">公钥</param>
        public static void CreateKeys(out string privateKey, out string publicKey)
        {
            // RSAKeyPairGenerator
            RsaKeyPairGenerator rsaKeyPairGen = new RsaKeyPairGenerator();
            rsaKeyPairGen.Init(new KeyGenerationParameters(new SecureRandom(), 2048));
            AsymmetricCipherKeyPair keyPair = rsaKeyPairGen.GenerateKeyPair();

            // Extract public and private key
            privateKey = ConvertPem(keyPair.Private);
            publicKey = ConvertPem(keyPair.Public);
        }

        private static string ConvertPem(AsymmetricKeyParameter keyParameters)
        {
            StringWriter stringWriter = new StringWriter();
            PemWriter pemWriter = new PemWriter(stringWriter);
            pemWriter.WriteObject(keyParameters);
            pemWriter.Writer.Flush();
            return stringWriter.GetStringBuilder().ToString();
        }
    }
}