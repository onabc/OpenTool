using OpenTool.Crypto.Enum;

namespace OpenTool.Crypto.Symmetric
{
    /// <summary>
    /// AES 对称算法
    /// </summary>
    public class AES : SymmetricCrypto
    {
        protected override SymmetricAlgorithm Algorithm => SymmetricAlgorithm.AES;

        public AES(string key, Padding padding = Padding.PKCS7Padding)
            : base(padding, key)
        {
        }

        public AES(string key, string iv, Padding padding = Padding.PKCS7Padding)
            : base(padding, key, iv)
        {
        }
    }
}