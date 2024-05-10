using OpenTool.Crypto.Enum;

namespace OpenTool.Crypto.Digest
{
    public class SHA256 : Digester
    {
        protected override DigestAlgorithm Algorithm => DigestAlgorithm.SHA256;
    }
}