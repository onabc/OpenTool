using OpenTool.Core.Extensions;
using OpenTool.Crypto.Enum;
using Org.BouncyCastle.Crypto;

namespace OpenTool.Crypto.Digest
{
    public abstract class Digester
    {
        private IDigest _digest;
        protected abstract DigestAlgorithm Algorithm { get; }

        public Digester()
        {
            _digest = DigesterFactory.GetDigestByAlgorithm(Algorithm);
        }

        public string Digest(string str)
        {
            byte[] hash = new byte[_digest.GetDigestSize()];
            byte[] bytes = str.StringToBytes();
            _digest.BlockUpdate(bytes, 0, bytes.Length);
            _digest.DoFinal(hash, 0);
            return BitConverter.ToString(hash).Replace("-", "").ToLower();
        }
    }
}