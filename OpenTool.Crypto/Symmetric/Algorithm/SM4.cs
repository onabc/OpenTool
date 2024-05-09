using OpenTool.Crypto.Enum;

namespace OpenTool.Crypto.Symmetric
{
    /// <summary>
    /// 国密SM4
    /// </summary>
    public class SM4 : SymmetricCrypto
    {
        protected override SymmetricAlgorithm Algorithm => SymmetricAlgorithm.SM4;

        public SM4(string key, Padding padding = Padding.PKCS7Padding)
           : base(padding, key)
        {
        }

        public SM4(string key, string iv, Padding padding = Padding.PKCS7Padding)
            : base(padding, key, iv)
        {
        }
    }
}